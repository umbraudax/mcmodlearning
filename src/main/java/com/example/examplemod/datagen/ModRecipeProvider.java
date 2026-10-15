package com.example.examplemod.datagen;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModItems;
import com.example.examplemod.learning;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }




    @Override
    protected void buildRecipes() {
        shaped(RecipeCategory.MISC, ModBlocks.AZURITE_BLOCK.asItem())
                .pattern("aaa")
                .pattern("aaa")
                .pattern("aaa")
                .define('a', ModItems.AZURITE)
                .unlockedBy("has_azurite", has(ModItems.AZURITE.get()))
                .group("azurite")
                .save(this.output);
        shapeless(RecipeCategory.MISC, ModItems.AZURITE.asItem(), 9)
                .requires(ModBlocks.AZURITE_BLOCK.asItem())
                .unlockedBy("has_azurite", has(ModItems.AZURITE.get()))
                .group("azurite")
                .save(this.output);

        shaped(RecipeCategory.MISC, ModBlocks.RAW_AZURITE_BLOCK.asItem())
                .pattern("aaa")
                .pattern("aaa")
                .pattern("aaa")
                .define('a', ModItems.RAW_AZURITE)
                .unlockedBy("has_raw_azurite", has(ModItems.RAW_AZURITE.get()))
                .group("azurite")
                .save(this.output);
        shapeless(RecipeCategory.MISC, ModItems.RAW_AZURITE.asItem(), 9)
                .requires(ModBlocks.RAW_AZURITE_BLOCK.asItem())
                .unlockedBy("has_raw_azurite", has(ModItems.RAW_AZURITE.get()))
                .group("azurite")
                .save(this.output);
    }
}
