package me.ez.cobblegen.Common.BlockEntites;

import me.ez.cobblegen.Init;
import me.ez.cobblegen.Util.InventoryUtil;
import me.ez.cobblegen.Util.LevelUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.concurrent.atomic.AtomicReference;

public class CobbleGeneratorBlockEntity extends BlockEntity {

    private int tick;

    public CobbleGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(Init.COBBLE_GENERATOR_BLOCKENTITY.get() , pos, state);
    }

    @SuppressWarnings("ConstantConditions")
    public static <E extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, E e) {
        if (!level.isClientSide){
            if (checkCanITick(e, 2)){
                if (LevelUtils.checkChestOnTop(level, pos)) {
                    BlockEntity assumedTopChest = level.getBlockEntity(pos.above());
                    assumedTopChest.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                        InventoryUtil.addItemToInventory(handler, Items.COBBLESTONE, 1);
                    });
                }
            }
        }
    }

    private static boolean checkCanITick(BlockEntity entity, int sec) {
        CobbleGeneratorBlockEntity cobbleGeneratorBlockEntity = (CobbleGeneratorBlockEntity) entity;
        cobbleGeneratorBlockEntity.tick++;
        if (cobbleGeneratorBlockEntity.tick > sec * 20) {
            cobbleGeneratorBlockEntity.tick = 0;
            return true;
        }
        return false;
    }

}
