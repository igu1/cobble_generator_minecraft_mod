package me.ez.cobblegen.Common.BlockEntity;

import me.ez.cobblegen.Common.Blocks.CobbleGenBlock;
import me.ez.cobblegen.Common.Container.CobbleGenContainer;
import me.ez.cobblegen.Common.Enums.LavaOrWater;
import me.ez.cobblegen.Common.Items.SpeedModule;
import me.ez.cobblegen.Init;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("ConstantConditions")
public class CobbleGenBlockEntity extends BaseContainerBlockEntity implements MenuProvider, WorldlyContainer {

    int tick;

    private NonNullList<ItemStack> items = NonNullList.withSize(CobbleGenContainer.SIZE, ItemStack.EMPTY);

    private final ItemStackHandler handler = new ItemStackHandler(CobbleGenContainer.SIZE) {
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if (stack.is(Items.WATER_BUCKET)){
                return slot == 0;
            }else if(stack.is(Items.LAVA_BUCKET)){
                return slot == 2;
            }else if(stack.is(Items.COBBLESTONE)){
                return slot == 1;
            }else{
                return false;
            }
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            return slot == 1 ? 64 : super.getStackLimit(slot, stack);
        }
    };

    private final LazyOptional<?> itemHandler = LazyOptional.of(this::createUnSidedHandler);


    public CobbleGenBlockEntity(BlockPos pos, BlockState state) {
        super(Init.COBBLE_GEN_BLOCK_ENTITY_TYPE_REGISTRY_OBJECT.get(), pos, state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T t) {
        setState(level, pos, state, t);
        if (!level.isClientSide) {
            int SPEED = getGeneratingSpeed(level, pos, state, t);
            CobbleGenBlockEntity blockEntity = (CobbleGenBlockEntity) t;
            blockEntity.tick++;
            if (blockEntity.tick > SPEED * 20) {
                blockEntity.getCapability(
                                CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                        .ifPresent(h -> {
                            addCobbleToInv(h, level, pos, state, (CobbleGenBlockEntity) t);
                        });
                blockEntity.tick = 0;
            }
        }
    }

    public static <T extends BlockEntity> void setState(Level level, BlockPos pos, BlockState state, T t) {
        t.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler1 -> {
            if (CobbleGenBlockEntity.checkStackInSlot(Items.WATER_BUCKET.getDefaultInstance(), 0, handler1) && CobbleGenBlockEntity.checkStackInSlot(Items.LAVA_BUCKET.getDefaultInstance(), 2, handler1)) {
                setStateWithValue(state, LavaOrWater.HAS_BOTH);
            } else if (CobbleGenBlockEntity.checkStackInSlot(Items.WATER_BUCKET.getDefaultInstance(), 0, handler1) && !CobbleGenBlockEntity.checkStackInSlot(Items.LAVA_BUCKET.getDefaultInstance(), 2, handler1)) {
                setStateWithValue(state, LavaOrWater.HAS_WATER);
            } else if (!CobbleGenBlockEntity.checkStackInSlot(Items.WATER_BUCKET.getDefaultInstance(), 0, handler1) && CobbleGenBlockEntity.checkStackInSlot(Items.LAVA_BUCKET.getDefaultInstance(), 2, handler1)) {
                setStateWithValue(state, LavaOrWater.HAS_LAVA);
            } else if (!CobbleGenBlockEntity.checkStackInSlot(Items.WATER_BUCKET.getDefaultInstance(), 0, handler1) && !CobbleGenBlockEntity.checkStackInSlot(Items.LAVA_BUCKET.getDefaultInstance(), 2, handler1)) {
                setStateWithValue(state, LavaOrWater.NO_BOTH);
            }
        });
    }

    private static void setStateWithValue(BlockState state, LavaOrWater value){
        state.setValue(CobbleGenBlock.LAVA_OR_WATER, value);
    }

    private static <T extends BlockEntity> int getGeneratingSpeed(Level level1, BlockPos pos, BlockState state1, T t) {

        AtomicInteger SPEED = new AtomicInteger(10);

        ItemStack MODULE_IRON_TIRE = Init.SPEED_MODULE_IRON_TIRE.get().getDefaultInstance();
        ItemStack MODULE_GOLD_TIRE = Init.SPEED_MODULE_GOLD_TIRE.get().getDefaultInstance();
        ItemStack MODULE_DIAMOND_TIRE = Init.SPEED_MODULE_DIAMOND_TIRE.get().getDefaultInstance();

        t.getCapability(
                CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                    if (handler.getStackInSlot(3).getItem() instanceof SpeedModule){
                        if (checkStackInSlot(MODULE_IRON_TIRE,3, handler)){
                            SPEED.set(8);
                        }else if (checkStackInSlot(MODULE_GOLD_TIRE,3, handler)){
                            SPEED.set(4);
                        }else if (checkStackInSlot(MODULE_DIAMOND_TIRE,3, handler)){
                            SPEED.set(1);
                        }else {
                            SPEED.set(10);
                        }
                    }
        });
        return SPEED.get();
    }

    public static boolean checkStackInSlot(ItemStack stack, int slot, IItemHandler handler){
        return handler.getStackInSlot(slot).is(stack.getItem());
    }

    private static void addCobbleToInv(IItemHandler h, Level level, BlockPos pos, BlockState state, CobbleGenBlockEntity t) {
        if (h.getSlotLimit(1) >= h.getStackInSlot(1).getCount()) {
            if (h.isItemValid(0, new ItemStack(Items.WATER_BUCKET)) && h.isItemValid(2, new ItemStack(Items.LAVA_BUCKET))) {
                if (h.getStackInSlot(0).is(Items.WATER_BUCKET) && h.getStackInSlot(2).is(Items.LAVA_BUCKET)) {
                    if (level.getBlockEntity(pos.above()) != null && level.getBlockEntity(pos.above()) instanceof BaseContainerBlockEntity) {
                            level.getBlockEntity(pos.above()).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(chestHandler -> {
                                for (int x = 0; x < chestHandler.getSlots(); x++) {
                                    if (chestHandler.isItemValid(x, Items.COBBLESTONE.getDefaultInstance())) {
                                        if (chestHandler.getStackInSlot(x).is(Items.COBBLESTONE) || chestHandler.getStackInSlot(x).isEmpty()) {
                                            chestHandler.insertItem(x, Items.COBBLESTONE.getDefaultInstance(), false);
                                            break;
                                        }
                                    }
                                }
                            });
                    } else {
                        h.insertItem(1, Items.COBBLESTONE.getDefaultInstance(), false);
                    }
                }
            }
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        ContainerHelper.saveAllItems(tag, items);
        tick = tag.getInt("tick");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.loadAllItems(tag, items);
        tag.putInt("tick", tick);
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Cobble Generator");
    }

    @Override
    protected Component getDefaultName() {
        return new TextComponent("Cobble Generator");
    }

    @Override
    protected AbstractContainerMenu createMenu(int i, Inventory inventory) {
        return new CobbleGenContainer(i, inventory, this);
    }

    // Implements Of WorldlyContainer

    @Override
    public int @NotNull [] getSlotsForFace(Direction direction) {
        return direction == Direction.DOWN ? new int[1] : new int[]{0, 2, 3};
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
        return this.canPlaceItem(slot, stack);
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(handler.getSlots());
        for (int i = 0; i < handler.getSlots(); i++) {
            inventory.setItem(i, handler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public boolean canTakeItemThroughFace(int i, ItemStack stack, Direction direction) {
        return i == 1 && stack.is(Items.COBBLESTONE) && direction == Direction.DOWN;
    }

    @Override
    public int getContainerSize() {
        return CobbleGenContainer.SIZE;
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int i) {
        return items.get(i);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return ContainerHelper.removeItem(items, slot, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int amount) {
        return ContainerHelper.takeItem(this.items, amount);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        items.set(slot, stack);
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D,
                    (double)this.worldPosition.getY() + 0.5D,
                    (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    //TODO
    //Netherite Module
}
