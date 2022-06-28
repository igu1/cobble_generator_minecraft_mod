package me.ez.cobblegen;

import me.ez.cobblegen.Common.BlockEntites.CobbleGeneratorBlockEntity;
import me.ez.cobblegen.Common.BlockEntites.CobbleGrinderBlockEntity;
import me.ez.cobblegen.Common.BlockEntites.ExtractorBlockEntity;
import me.ez.cobblegen.Common.Blocks.CobbleGenerator;
import me.ez.cobblegen.Common.Blocks.CobbleGrinder;
import me.ez.cobblegen.Common.Blocks.Extractor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class Init {
    //Def Registries
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTITY_TYPE = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Main.MOD_ID);

    //Registering
    public static final RegistryObject<Block> COBBLE_GENERATOR = BLOCKS.register("cobble_generator", () -> new CobbleGenerator(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
    public static final RegistryObject<Block> COBBLE_GRINDER = BLOCKS.register("cobble_grinder", () -> new CobbleGrinder(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));
    public static final RegistryObject<Block> EXTRACTOR = BLOCKS.register("extractor", () -> new Extractor(BlockBehaviour.Properties.of(Material.STONE).strength(3.5F)));


    public static final RegistryObject<BlockItem> COBBLE_GENERATOR_ITEM = ITEMS.register("cobble_generator", () -> new BlockItem(COBBLE_GENERATOR.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<BlockItem> COBBLE_GRINDER_ITEM = ITEMS.register("cobble_grinder", () -> new BlockItem(COBBLE_GRINDER.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    public static final RegistryObject<BlockItem> EXTRACTOR_ITEM = ITEMS.register("extractor", () -> new BlockItem(EXTRACTOR.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));


    public static final RegistryObject<BlockEntityType<CobbleGeneratorBlockEntity>> COBBLE_GENERATOR_BLOCKENTITY = BLOCKENTITY_TYPE.register("cobble_generator", () -> BlockEntityType.Builder.of(CobbleGeneratorBlockEntity::new, COBBLE_GENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType<CobbleGrinderBlockEntity>> COBBLE_GRINDER_BLOCKENTITY = BLOCKENTITY_TYPE.register("cobble_grinder", () -> BlockEntityType.Builder.of(CobbleGrinderBlockEntity::new, COBBLE_GRINDER.get()).build(null));
    public static final RegistryObject<BlockEntityType<ExtractorBlockEntity>> EXTRACTOR_BLOCKENTITY = BLOCKENTITY_TYPE.register("extractor", () -> BlockEntityType.Builder.of(ExtractorBlockEntity::new, EXTRACTOR.get()).build(null));

}
