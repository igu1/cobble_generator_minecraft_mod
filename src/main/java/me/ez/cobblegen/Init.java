package me.ez.cobblegen;

import me.ez.cobblegen.Common.BlockEntity.CobbleGenBlockEntity;
import me.ez.cobblegen.Common.Blocks.CobbleGenBlock;
import me.ez.cobblegen.Common.Container.CobbleGenContainer;
import me.ez.cobblegen.Common.Items.SpeedModule;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Init {
    public static final DeferredRegister<Block> BLOCK_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID);
    public static final DeferredRegister<Item> ITEM_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Main.MOD_ID);
    public static final DeferredRegister<MenuType<?>> CONTAINER_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.CONTAINERS, Main.MOD_ID);

    public static final RegistryObject<CobbleGenBlock> COBBLE_GEN_BLOCK_REGISTRY_OBJECT = BLOCK_DEFERRED_REGISTER.register("cobble_gen",
            () -> new CobbleGenBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));

    public static final RegistryObject<BlockItem> COBBLE_GEN_BLOCK_ITEM_REGISTRY_OBJECT = ITEM_DEFERRED_REGISTER.register("cobble_gen",
            () -> new BlockItem(Init.COBBLE_GEN_BLOCK_REGISTRY_OBJECT.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<BlockEntityType<CobbleGenBlockEntity>> COBBLE_GEN_BLOCK_ENTITY_TYPE_REGISTRY_OBJECT = BLOCK_ENTITY_DEFERRED_REGISTER.register("cobble_gen",
            () -> BlockEntityType.Builder.of(CobbleGenBlockEntity::new,
                    Init.COBBLE_GEN_BLOCK_REGISTRY_OBJECT.get()).build(null));

    public static final RegistryObject<MenuType<CobbleGenContainer>> COBBLE_GEN_CONTAINER_REGISTRY_OBJECT =
            CONTAINER_DEFERRED_REGISTER.register("cobble_gen",
                    () -> IForgeMenuType.create(CobbleGenContainer::new));

    //MODULES
    public static final RegistryObject<SpeedModule> SPEED_MODULE_IRON_TIRE = ITEM_DEFERRED_REGISTER.register("speed_module_iron_tire", SpeedModule::new);

    public static final RegistryObject<SpeedModule> SPEED_MODULE_GOLD_TIRE = ITEM_DEFERRED_REGISTER.register("speed_module_gold_tire", SpeedModule::new);

    public static final RegistryObject<SpeedModule> SPEED_MODULE_DIAMOND_TIRE = ITEM_DEFERRED_REGISTER.register("speed_module_diamond_tire", SpeedModule::new);
}
