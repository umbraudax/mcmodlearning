package com.example.examplemod.datagen;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModItems;
import com.example.examplemod.learning;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, learning.MODID);
    }


    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        /* ITEMS */
        itemModels.generateFlatItem(ModItems.AZURITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_AZURITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.METAL_DETECTOR.get(), ModelTemplates.FLAT_ITEM);


        /* BLOCKS */
//        blockModels.createTrivialCube(ModBlocks.AZURITE_BLOCK.get());
        ModBlocks.BlockArray.forEach(b -> {
            blockModels.createTrivialCube(b.get());
        });

    }
}
