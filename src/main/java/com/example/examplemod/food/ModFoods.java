package com.example.examplemod.food;

import com.example.examplemod.food.consumeeffects.ChatLogConsumeEffect;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class ModFoods {
    public static final FoodProperties ONION = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4f).build();
    public static final Consumable ONION_CONSUMABLE = Consumables.defaultFood()
            .consumeSeconds(2f)
            .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.BREATH_OF_THE_NAUTILUS, 1000), 0.5f))
            .onConsume(new ChatLogConsumeEffect("message.learning.onion_eat", List.of(ChatLogConsumeEffect.Injections.SATURATION)))
            .build();
}
