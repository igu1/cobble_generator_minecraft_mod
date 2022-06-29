//package me.ez.cobblegen.Common.BlockEntites;
//
//import me.ez.cobblegen.Init;
//import me.ez.cobblegen.Util.InventoryUtil;
//import me.ez.cobblegen.Util.LevelUtils;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraftforge.items.CapabilityItemHandler;
//import net.minecraftforge.items.IItemHandler;
//
//import java.util.List;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//public class ExtractorBlockEntity extends BlockEntity {
//
//    private int tick;
//
//    public ExtractorBlockEntity(BlockPos pos, BlockState state) {
//        super(Init.EXTRACTOR_BLOCKENTITY.get(), pos, state);
//    }
//
//    public static <E extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState state, E e) {
//        if (!level.isClientSide) {
//            if (checkCanITick(e, 5)) {
//                if (LevelUtils.checkBothSideHaveChest(level, blockPos)) {
//                    if (LevelUtils.checkIfBothSideChestHasHandler(level, blockPos)) {
//                        BlockEntity topChest = level.getBlockEntity(blockPos.above());
//                        BlockEntity belowChest = level.getBlockEntity(blockPos.below());
//                        if (topChest != null && belowChest != null) {
//                            AtomicBoolean itemFound = new AtomicBoolean(false);
//                            topChest.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
//                                if (InventoryUtil.checkIsItemInInventory(handler, Items.SAND)) {
//                                    InventoryUtil.removeItemFromInventory(handler, Items.SAND, 1);
//                                    itemFound.set(true);
//                                }
//                            });
//                            List<Item> items = List.of(
//                                    Items.STICK,
//                                    Items.GRAVEL,
//                                    Items.DEAD_BUSH,
//                                    Items.GOLD_NUGGET
//                            );
//
//                            belowChest.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
//                                if (itemFound.get() == true) {
//                                    addItemsFromList(items, level, handler);
//                                    itemFound.set(false);
//                                }
//                            });
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    private static void addItemsFromList(List<Item> items, Level level, IItemHandler handler) {
//        items.forEach(item -> {
//            if (item != Items.GOLD_NUGGET) {
//                if (InventoryUtil.hasSpace(handler, item)) {
//                    InventoryUtil.addItemToInventory(handler, item, level.random.nextInt(1));
//                }
//            }else {
//                if (level.random.nextInt(10) == 5) {
//                    if (InventoryUtil.hasSpace(handler, item)) {
//                        InventoryUtil.addItemToInventory(handler, item, level.random.nextInt(2));
//                    }
//                }
//            }
//        });
//    }
//
//    private static boolean checkCanITick(BlockEntity entity, int sec) {
//        ExtractorBlockEntity extractorBlockEntity = (ExtractorBlockEntity) entity;
//        extractorBlockEntity.tick++;
//        if (extractorBlockEntity.tick > sec * 20) {
//            extractorBlockEntity.tick = 0;
//            return true;
//        }
//        return false;
//    }
//}
