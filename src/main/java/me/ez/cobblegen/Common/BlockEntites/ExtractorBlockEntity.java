package me.ez.cobblegen.Common.BlockEntites;

import me.ez.cobblegen.Init;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ExtractorBlockEntity extends BlockEntity {
    public ExtractorBlockEntity(BlockPos pos, BlockState state) {
        super(Init.EXTRACTOR_BLOCKENTITY.get(), pos, state);
    }
}
