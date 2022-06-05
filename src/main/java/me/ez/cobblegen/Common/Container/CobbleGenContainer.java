package me.ez.cobblegen.Common.Container;

import me.ez.cobblegen.Common.BlockEntity.CobbleGenBlockEntity;
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

    public CobbleGenContainer(int i, Inventory inventory, FriendlyByteBuf data) {
        this(i, inventory, Objects.requireNonNull(inventory.player.level.getBlockEntity(data.readBlockPos())));
    }

    public CobbleGenContainer(int i, Inventory inventory, BlockEntity blockEntity) {
        super(Init.COBBLE_GEN_CONTAINER_REGISTRY_OBJECT.get(), i);
        this.BLOCK_ENTITY = (CobbleGenBlockEntity) blockEntity;
        this.LEVEL = inventory.player.level;
        checkContainerSize(inventory, SIZE);

        assert blockEntity != null;
        blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
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

//    private static final int PLAYER_INVENTORY_SLOT_COUNT = 9 * 3;
//    private static final int HOTBAR_SLOT_COUNT = 9;
//    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
//    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
//    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
//    private static final int TE_INVENTORY_SLOT_COUNT = CobbleGenContainer.SIZE;
//
//
//    @Override
//    public ItemStack quickMoveStack(Player playerIn, int index) {
//        Slot sourceSlot = slots.get(index);
//        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
//
//        ItemStack sourceStack = sourceSlot.getItem();
//        ItemStack copyOfSourceStack = sourceStack.copy();
//
//        if (index < VANILLA_SLOT_COUNT) {
//            // This is a vanilla container slot so merge the stack into the tile inventory
//            if (!moveItemStackTo(sourceStack, 36, 36
//                    + TE_INVENTORY_SLOT_COUNT, false)) {
//                return ItemStack.EMPTY;
//            }
//        } else if (index < 36 + TE_INVENTORY_SLOT_COUNT) {
//            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
//                return ItemStack.EMPTY;
//            }
//        } else {
//            System.out.println("Invalid slotIndex:" + index);
//            return ItemStack.EMPTY;
//        }
//
//        if (sourceStack.getCount() == 0) {
//            sourceSlot.set(ItemStack.EMPTY);
//        } else {
//            sourceSlot.setChanged();
//        }
//        sourceSlot.onTake(playerIn, sourceStack);
//        return copyOfSourceStack;
//    }
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
