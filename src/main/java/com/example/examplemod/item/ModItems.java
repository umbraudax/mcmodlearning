package com.example.examplemod.item;

import com.example.examplemod.food.ModFoods;
import com.example.examplemod.item.custom.MetalDetectorItem;
import com.example.examplemod.learning;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(learning.MODID);

    public static final DeferredItem<Item> AZURITE = ITEMS.registerSimpleItem("azurite");
    public static final DeferredItem<Item> RAW_AZURITE = ITEMS.registerSimpleItem("raw_azurite");

    public static final DeferredItem<Item> ONION = ITEMS.registerSimpleItem("onion",
            properties -> properties.food(ModFoods.ONION, ModFoods.ONION_CONSUMABLE));

    public static final DeferredItem<Item> END_FIRE_STARTER = ITEMS.registerItem("end_fire_starter", properties -> new Item(
            properties
                    .stacksTo(32)
                    .component(DataComponents.COOKING_FUEL, new CookingFuel(new ResolvableInt.Constant(600), new ResolvableFloat.Constant(1.0f)))
                    .component(DataComponents.LORE, new ItemLore(List.of(
                            Component.translatable("tooltip.learning.end_fire_starter")
                    )))));


    public static final DeferredItem<Item> METAL_DETECTOR = ITEMS.registerItem("metal_detector", MetalDetectorItem::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
