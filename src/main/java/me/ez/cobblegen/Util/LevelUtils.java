package me.ez.cobblegen.Util;

import me.ez.cobblegen.Common.BlockEntites.CobbleGeneratorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.CapabilityItemHandler;

public class LevelUtils {

    public static boolean checkChestOnTop(Level level, BlockPos pos) {
        BlockPos topChest = pos.above();
        if (level.getBlockEntity(topChest) != null) {
            BlockEntity assumedTopChest = level.getBlockEntity(topChest);
            if (assumedTopChest instanceof RandomizableContainerBlockEntity) {
                assumedTopChest.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {

                });
                return true;
            }
        }
        return false;
    }

    public static boolean checkBothSideHaveChest(Level level , BlockPos pos) {
        BlockPos topChest = pos.above();
        BlockPos belowChest = pos.below();
        if (level.getBlockEntity(topChest) != null && level.getBlockEntity(belowChest) != null) {
            BlockEntity assumedTopChest = level.getBlockEntity(topChest);
            BlockEntity assumedBelowChest = level.getBlockEntity(belowChest);
            if (assumedTopChest instanceof RandomizableContainerBlockEntity &&
                    assumedBelowChest instanceof RandomizableContainerBlockEntity) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkIfBothSideChestHasHandler(Level level, BlockPos blockPos) {
        BlockPos topChest = blockPos.above();
        BlockPos belowChest = blockPos.below();
        if (level.getBlockEntity(topChest) != null && level.getBlockEntity(belowChest) != null) {
            BlockEntity assumedTopChest = level.getBlockEntity(topChest);
            BlockEntity assumedBelowChest = level.getBlockEntity(belowChest);
            if (assumedTopChest instanceof RandomizableContainerBlockEntity &&
                    assumedBelowChest instanceof RandomizableContainerBlockEntity) {
                if (assumedBelowChest.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).isPresent()
                        && assumedTopChest.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).isPresent()) {
                    return true;
                }
            }
        }
        return false;
    }

}
