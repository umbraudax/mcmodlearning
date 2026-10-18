package com.example.examplemod.datagen;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModItems;
import com.example.examplemod.learning;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

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
                .unlockedBy(getHasName(ModItems.AZURITE), has(ModItems.AZURITE.get()))
                .group(getItemName(ModItems.AZURITE))
                .save(this.output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.AZURITE_STAIRS.asItem(), 6)
                .pattern("a  ")
                .pattern("aa ")
                .pattern("aaa")
                .define('a', ModBlocks.AZURITE_BLOCK.asItem())
                .unlockedBy(getHasName(ModItems.AZURITE), has(ModItems.AZURITE.get()))
                .group(getItemName(ModItems.AZURITE))
                .save(this.output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.AZURITE_SLAB.asItem(), 3)
                .pattern("aaa")
                .define('a', ModBlocks.AZURITE_BLOCK.asItem())
                .unlockedBy(getHasName(ModItems.AZURITE), has(ModItems.AZURITE.get()))
                .group(getItemName(ModItems.AZURITE))
                .save(this.output);

        shapeless(RecipeCategory.MISC, ModItems.AZURITE.asItem(), 9)
                .requires(ModBlocks.AZURITE_BLOCK.asItem())
                .unlockedBy(getHasName(ModItems.AZURITE), has(ModItems.AZURITE.get()))
                .group(getItemName(ModItems.AZURITE))
                .save(this.output);

        shaped(RecipeCategory.MISC, ModBlocks.RAW_AZURITE_BLOCK.asItem())
                .pattern("aaa")
                .pattern("aaa")
                .pattern("aaa")
                .define('a', ModItems.RAW_AZURITE)
                .unlockedBy(getHasName(ModItems.RAW_AZURITE), has(ModItems.RAW_AZURITE.get()))
                .group(getItemName(ModItems.AZURITE))
                .save(this.output);
        shapeless(RecipeCategory.MISC, ModItems.RAW_AZURITE.asItem(), 9)
                .requires(ModBlocks.RAW_AZURITE_BLOCK.asItem())
                .unlockedBy(getHasName(ModItems.RAW_AZURITE), has(ModItems.RAW_AZURITE.get()))
                .group(getItemName(ModItems.AZURITE))
                .save(this.output);

        shaped(RecipeCategory.TOOLS, ModItems.METAL_DETECTOR.asItem())
                .pattern("  S")
                .pattern(" A ")
                .pattern("SI ")
                .define('S', Items.STICK.asItem())
                .define('A', ModItems.AZURITE.asItem())
                .define('I', Items.IRON_INGOT.asItem())
                .unlockedBy(getHasName(ModItems.AZURITE), has(ModItems.AZURITE.get()))
                .group(getItemName(ModItems.AZURITE))
                .save(this.output);

        oreSmelting(
                List.of(
                        ModItems.RAW_AZURITE.get(),
                        ModBlocks.AZURITE_ORE.asItem(),
                        ModBlocks.AZURITE_NETHER_ORE.asItem(),
                        ModBlocks.AZURITE_END_ORE.asItem(),
                        ModBlocks.AZURITE_DEEPSLATE_ORE.asItem()
                ),
                RecipeCategory.MISC,
                CookingBookCategory.MISC,
                ModItems.AZURITE.get(),
                0.25f,
                200,
                getItemName(ModItems.AZURITE)
        );
        oreBlasting(
                List.of(
                        ModItems.RAW_AZURITE.get(),
                        ModBlocks.AZURITE_ORE.asItem(),
                        ModBlocks.AZURITE_NETHER_ORE.asItem(),
                        ModBlocks.AZURITE_END_ORE.asItem(),
                        ModBlocks.AZURITE_DEEPSLATE_ORE.asItem()
                ),
                RecipeCategory.MISC,
                CookingBookCategory.MISC,
                ModItems.AZURITE.get(),
                0.5f,
                100,
                getItemName(ModItems.AZURITE)
        );

    }

    @Override
    protected <T extends AbstractCookingRecipe> void oreCooking(
            AbstractCookingRecipe.Factory<T> factory,
            List<ItemLike> smeltables,
            RecipeCategory craftingCategory,
            CookingBookCategory cookingCategory,
            ItemLike result,
            float experience,
            int cookingTime,
            String group,
            String fromDesc
    ) {
        for (ItemLike item : smeltables) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(item), craftingCategory, cookingCategory, result, experience, cookingTime, factory)
                    .group(group)
                    .unlockedBy(getHasName(item), this.has(item))
                    .save(this.output, learning.MODID + ":" + getItemName(result) + fromDesc + "_" + getItemName(item));
        }
    }



}
