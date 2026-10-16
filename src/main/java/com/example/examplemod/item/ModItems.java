package com.example.examplemod.item;

import com.example.examplemod.item.custom.MetalDetectorItem;
import com.example.examplemod.learning;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(learning.MODID);

    public static final DeferredItem<Item> AZURITE = ITEMS.registerSimpleItem("azurite");
    public static final DeferredItem<Item> RAW_AZURITE = ITEMS.registerSimpleItem("raw_azurite");


    public static final DeferredItem<Item> METAL_DETECTOR = ITEMS.registerItem("metal_detector", MetalDetectorItem::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
