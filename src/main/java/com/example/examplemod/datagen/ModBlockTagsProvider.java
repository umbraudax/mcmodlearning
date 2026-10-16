package com.example.examplemod.datagen;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.learning;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {


    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, learning.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getRK(ModBlocks.AZURITE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.AZURITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.AZURITE_NETHER_ORE))
                .add(ModBlocks.getRK(ModBlocks.AZURITE_END_ORE))
                .add(ModBlocks.getRK(ModBlocks.AZURITE_DEEPSLATE_ORE))
                .add(ModBlocks.getRK(ModBlocks.RAW_AZURITE_BLOCK));


        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.getRK(ModBlocks.AZURITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.AZURITE_NETHER_ORE))
                .add(ModBlocks.getRK(ModBlocks.AZURITE_END_ORE))
                .add(ModBlocks.getRK(ModBlocks.AZURITE_DEEPSLATE_ORE));

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.getRK(ModBlocks.AZURITE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.RAW_AZURITE_BLOCK));

        tag(BlockTags.ORES)
                .add(ModBlocks.getRK(ModBlocks.AZURITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.AZURITE_NETHER_ORE))
                .add(ModBlocks.getRK(ModBlocks.AZURITE_END_ORE))
                .add(ModBlocks.getRK(ModBlocks.AZURITE_DEEPSLATE_ORE));



    }
}
