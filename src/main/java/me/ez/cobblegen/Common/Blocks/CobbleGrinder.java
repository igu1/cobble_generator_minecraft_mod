//package me.ez.cobblegen.Common.Blocks;
//
//import me.ez.cobblegen.Common.AbstractContainerBlockEntity.AbstractBlockEntityBlock;
//import me.ez.cobblegen.Common.BlockEntites.CobbleGrinderBlockEntity;
//import me.ez.cobblegen.Init;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntityTicker;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.phys.BlockHitResult;
//import org.jetbrains.annotations.Nullable;
//
//public class CobbleGrinder extends AbstractBlockEntityBlock {
//    public CobbleGrinder(Properties properties) {
//        super(properties);
//    }
//
//    @Override
//    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
//        return new CobbleGrinderBlockEntity(pos, state);
//    }
//
//    @Override
//    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
//        return createTickerHelper(type, Init.COBBLE_GRINDER_BLOCKENTITY.get(), CobbleGrinderBlockEntity::tick);
//    }
//
//    @Override
//    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand interactionHand, BlockHitResult hitResult) {
//        return InteractionResult.CONSUME;
//    }
//
//    @Override
//    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState blockState, boolean b) {}
//}
