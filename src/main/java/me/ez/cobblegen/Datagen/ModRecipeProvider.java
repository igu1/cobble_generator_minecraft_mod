package me.ez.cobblegen.Datagen;

import me.ez.cobblegen.Init;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.io.IOException;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder
                .shaped(Init.COBBLE_GENERATOR_ITEM.get())
                .define('#', Items.DEEPSLATE_BRICKS)
                .define('L', Items.LAVA_BUCKET)
                .define('W', Items.WATER_BUCKET)
                .define('G', Items.GLASS)
                .pattern("###")
                .pattern("LGW")
                .pattern("###")
                .unlockedBy(
                        "has_deepslate_bricks",
                        has(Items.DEEPSLATE_BRICKS))
                .save(consumer);

    }
}
