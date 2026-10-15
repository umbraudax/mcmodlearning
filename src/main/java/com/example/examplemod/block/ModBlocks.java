package com.example.examplemod.block;

import com.example.examplemod.item.ModItems;
import com.example.examplemod.learning;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(learning.MODID);



    public static final DeferredBlock<Block> AZURITE_BLOCK = registerBlock(
            "azurite_block",
            properties -> new Block(properties
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> AZURITE_ORE = registerBlock(
                    "azurite_ore",
                    properties -> new Block(properties
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)
                    ));

    public static final DeferredBlock<Block> AZURITE_NETHER_ORE = registerBlock(
                    "azurite_nether_ore",
                    properties -> new Block(properties
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.NETHER_GOLD_ORE)
                    ));

    public static final DeferredBlock<Block> AZURITE_END_ORE = registerBlock(
                    "azurite_end_ore",
                    properties -> new Block(properties
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)
                    ));
    public static final DeferredBlock<Block> AZURITE_DEEPSLATE_ORE = registerBlock(
                    "azurite_deepslate_ore",
                    properties -> new Block(properties
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.DEEPSLATE)
                    ));

    public static final DeferredBlock<Block> RAW_AZURITE_BLOCK = registerBlock(
                    "raw_azurite_block",
                    properties -> new Block(properties
                            .strength(4f)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.IRON)
                    ));

    public static final List<DeferredBlock<Block>> BlockArray = List.of(
            AZURITE_BLOCK,
            AZURITE_ORE,
            AZURITE_NETHER_ORE,
            AZURITE_END_ORE,
            AZURITE_DEEPSLATE_ORE,
            RAW_AZURITE_BLOCK
    );

    public static ResourceKey<Block> getRK(DeferredBlock<Block> block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block.get()).get(); // maybe change later
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(
                name,
                properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()
                ));

    }

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
