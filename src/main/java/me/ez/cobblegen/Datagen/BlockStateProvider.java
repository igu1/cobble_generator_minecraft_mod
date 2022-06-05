package me.ez.cobblegen.Datagen;

import me.ez.cobblegen.Common.Blocks.CobbleGenBlock;
import me.ez.cobblegen.Init;
import me.ez.cobblegen.Main;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class BlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {

    public BlockStateProvider(DataGenerator gen, String modid, ExistingFileHelper exFileHelper) {
        super(gen, modid, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        makeBlockEntity(Init.COBBLE_GEN_BLOCK_REGISTRY_OBJECT.get(), "cobblegen", "cobblegen");
    }

    public void makeBlockEntity(Block block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);
        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, Block block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cubeAll(modelName + "_" + state.getValue(CobbleGenBlock.LAVA_OR_WATER).getSerializedName(),
                new ResourceLocation(Main.MOD_ID, "block/" + textureName + "_" +  state.getValue(CobbleGenBlock.LAVA_OR_WATER).getSerializedName())));
        return models;
    }
}
