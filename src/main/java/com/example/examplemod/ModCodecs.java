package com.example.examplemod;

import com.example.examplemod.food.consumeeffects.ChatLogConsumeEffect;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;

public abstract class ModCodecs {
    public static final Codec<ChatLogConsumeEffect.Injections> INJECTIONS_CODEC =
            StringRepresentable.fromEnum(ChatLogConsumeEffect.Injections::values);

    public static final StreamCodec<ByteBuf, ChatLogConsumeEffect.Injections> INJECTIONS_STREAM_CODEC =
            ByteBufCodecs.fromCodec(INJECTIONS_CODEC);

}
