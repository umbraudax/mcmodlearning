package com.example.examplemod.food.consumeeffects;

import com.example.examplemod.ModCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Optional;

public record ChatLogConsumeEffect(
        String messageKey,
        List<Injections> injectionsList
) implements ConsumeEffect {

    public enum Injections implements StringRepresentable {
        SATURATION("saturation");

        public final String serializedName;

        Injections(String serializedName) {
            this.serializedName = serializedName;
        }

        @Override
        public @NonNull String getSerializedName() {
            return this.serializedName;
        }
    }


    public static final MapCodec<ChatLogConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.STRING.fieldOf("message").forGetter(ChatLogConsumeEffect::messageKey),
                    ModCodecs.INJECTIONS_CODEC.listOf().optionalFieldOf("injections", List.of()).forGetter(ChatLogConsumeEffect::injectionsList))
                    .apply(instance, ChatLogConsumeEffect::new));




    public static final StreamCodec<RegistryFriendlyByteBuf, ChatLogConsumeEffect> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(Codec.STRING),
            ChatLogConsumeEffect::messageKey,
            ModCodecs.INJECTIONS_STREAM_CODEC.apply(ByteBufCodecs.list()),
            ChatLogConsumeEffect::injectionsList,
            ChatLogConsumeEffect::new
    );


    public static final ConsumeEffect.Type<ChatLogConsumeEffect> CHAT_LOG_EFFECT = Registry.register(BuiltInRegistries.CONSUME_EFFECT_TYPE, "chat_log", new ConsumeEffect.Type<>(CODEC, STREAM_CODEC));


    @Override
    public @NonNull Type<? extends ConsumeEffect> getType() {
        return ChatLogConsumeEffect.CHAT_LOG_EFFECT;
    }



    @Override
    public boolean apply(Level level, ItemStack stack, LivingEntity user) {


        if (user instanceof ServerPlayer player) {
            Component sendComponent;
            if (!this.injectionsList.isEmpty()) {
                sendComponent = Component.translatable(messageKey, injectionsList.stream().map(Injections::getSerializedName).toArray());
            } else {
                sendComponent = Component.literal(messageKey);
            }
            player.sendSystemMessage(sendComponent);
            return true;
        }
        return false;
    }
}
