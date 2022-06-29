package me.ez.cobblegen.Common.BlockEntites;

import me.ez.cobblegen.Init;
import me.ez.cobblegen.Util.InventoryUtil;
import me.ez.cobblegen.Util.LevelUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.concurrent.atomic.AtomicBoolean;

public class CobbleGrinderBlockEntity extends BlockEntity {

    private int tick;

    public CobbleGrinderBlockEntity(BlockPos pos, BlockState state) {
        super(Init.COBBLE_GRINDER_BLOCKENTITY.get(), pos, state);
    }

    public static <E extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState state, E e) {
        if (!level.isClientSide) {
            if (checkCanITick(e, 5)) {
                if (LevelUtils.checkBothSideHaveChest(level, blockPos)) {
                    if (LevelUtils.checkIfBothSideChestHasHandler(level, blockPos)) {
                        AtomicBoolean itemFound = new AtomicBoolean(false);
                        level.getBlockEntity(blockPos.above()).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                            if (InventoryUtil.checkIsItemInInventory(handler, Items.COBBLESTONE)) {
                                InventoryUtil.removeItemFromInventory(handler, Items.COBBLESTONE, 1);
                                itemFound.set(true);
                            }
                        });
                        if (itemFound.get()) {
                            level.getBlockEntity(blockPos.below()).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                                if (InventoryUtil.hasSpace(handler, Items.GRAVEL)) {
                                    InventoryUtil.addItemToInventory(handler, Items.GRAVEL, 1);
                                    itemFound.set(false);
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    private static boolean checkCanITick(BlockEntity entity, int sec) {
        CobbleGrinderBlockEntity cobbleGrinderBlockEntity = (CobbleGrinderBlockEntity) entity;
        cobbleGrinderBlockEntity.tick++;
        if (cobbleGrinderBlockEntity.tick > sec * 20) {
            cobbleGrinderBlockEntity.tick = 0;
            return true;
        }
        return false;
    }
}
