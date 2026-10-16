package com.example.examplemod;

import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

public abstract class ModUtils {
    public static void sendSoundToPlayer(Player player, Holder<SoundEvent> sound, double x, double y, double z, float volume, float pitch) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.connection.send(new ClientboundSoundPacket(
                    Holder.direct(sound.value()),
                    SoundSource.PLAYERS,
                    x, y, z,
                    volume,
                    pitch,
                    player.getRandom().nextLong()
            ));
        }
    }

    public static void sendMDPlingToPlayer(Player player, double x, double y, double z, float volume) {
        sendSoundToPlayer(player, SoundEvents.NOTE_BLOCK_PLING, x, y, z, volume, 2.0f);
    }

    public static void sendSimpleParticleToLevel(Level level, SimpleParticleType particle, double x, double y, double z, int count, double xDist, double yDist, double zDist, double speed) {
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    particle,
                    x, y, z,
                    count,
                    xDist, yDist, zDist,
                    speed
            );
        }
    }

    public static void sendHappyVillagerEffectToLevel(Level level, double x, double y, double z) {
        sendSimpleParticleToLevel(level, ParticleTypes.HAPPY_VILLAGER, x, y, z, 20, 1, 1, 1, 2);
    }
}
