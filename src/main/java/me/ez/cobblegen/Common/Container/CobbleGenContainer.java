package me.ez.cobblegen.Common.Container;

import me.ez.cobblegen.Common.BlockEntity.CobbleGenBlockEntity;
import me.ez.cobblegen.Common.Items.SpeedModule;
import me.ez.cobblegen.Common.Slots.CobbleGen.ModuleCobbleSlot;
import me.ez.cobblegen.Common.Slots.CobbleGen.ResultCobbleSlot;
import me.ez.cobblegen.Init;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CobbleGenContainer extends AbstractContainerMenu {

    public static final int SIZE = 4;
    private final CobbleGenBlockEntity BLOCK_ENTITY;
    private final Level LEVEL;
    private final Inventory inventory;

    public CobbleGenContainer(int i, Inventory inventory, FriendlyByteBuf data) {
        this(i, inventory, Objects.requireNonNull(inventory.player.level.getBlockEntity(data.readBlockPos())));
    }

    public CobbleGenContainer(int i, Inventory inventory, BlockEntity blockEntity) {
        super(Init.COBBLE_GEN_CONTAINER_REGISTRY_OBJECT.get(), i);
        this.BLOCK_ENTITY = (CobbleGenBlockEntity) blockEntity;
        this.LEVEL = inventory.player.level;
        this.inventory = inventory;
        checkContainerSize(inventory, SIZE);

        assert blockEntity != null;
//        this.addSlot(new Slot(this.inventory, 0, 26, 36) {
//            @Override
//            public boolean mayPlace(ItemStack stack) {
//                return stack == Items.WATER_BUCKET.getDefaultInstance();
//            }
//        });
//        this.addSlot(new Slot(this.inventory, 1, 80, 36) {
//            @Override
//            public boolean mayPlace(ItemStack stack) {
//                return false;
//            }
//        });
//        this.addSlot(new Slot(this.inventory, 2, 134, 36){
//            @Override
//            public boolean mayPlace(ItemStack stack) {
//                return stack == Items.LAVA_BUCKET.getDefaultInstance();
//            }
//        });
//        this.addSlot(new Slot(this.inventory, 3, 152, 8){
//            @Override
//            public boolean mayPlace(ItemStack stack) {
//                return stack.getItem() instanceof SpeedModule;
//            }
//        });

        blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, Direction.NORTH).ifPresent(h -> {
            this.addSlot(new SlotItemHandler(h, 0,  26, 36));
            this.addSlot(new ResultCobbleSlot(h, 1,  80, 36));
            this.addSlot(new SlotItemHandler(h, 2,  134, 36));
            this.addSlot(new ModuleCobbleSlot(h, 3,  152, 8));
        });

        playerInv(inventory);
        playerHotbar(inventory);
    }


    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(
                        this.LEVEL,
                        BLOCK_ENTITY.getBlockPos()),
                player,
                Init.COBBLE_GEN_BLOCK_REGISTRY_OBJECT.get());
    }

@Override
public ItemStack quickMoveStack(Player player, int index) {
    ItemStack itemstack = ItemStack.EMPTY;
    Slot slot = slots.get(index);
    if (slot.hasItem()) {
        ItemStack itemstack1 = slot.getItem();
        itemstack = itemstack1.copy();
        if (index < SIZE) {
            //                                         36 + 4 = Total Slots
            if (!this.moveItemStackTo(itemstack1, SIZE, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else if (!this.moveItemStackTo(itemstack1, 0, SIZE, false)) {
            return ItemStack.EMPTY;
        }
        if (itemstack1.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
    }

    return itemstack;
}


    private void playerInv(Inventory inventory){
        for(int i1 = 0; i1 < 3; ++i1) {
            for(int k1 = 0; k1 < 9; ++k1) {
                this.addSlot(new Slot(inventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 84 + i1 * 18));
            }
        }
    }

    private void playerHotbar(Inventory inventory){
        for(int j1 = 0; j1 < 9; ++j1) {
            this.addSlot(new Slot(inventory, j1, 8 + j1 * 18, 142));
        }
    }
}
