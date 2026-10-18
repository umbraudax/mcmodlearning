package com.example.examplemod.tags;

import com.example.examplemod.learning;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {
        public static TagKey<Block> METAL_DETECTOR_VALUABLES = createTag("metal_detector_valuables");

        public static TagKey<Block> createTag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(learning.MODID, name));
        }
    }

    public static class Items {
        public static TagKey<Item> MAGIC_BLOCK_TRANSFORMABLE = createTag("magic_block_transformable");

        public static TagKey<Item> createTag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(learning.MODID, name));
        }
    }
}
