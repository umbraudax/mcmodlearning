package com.example.examplemod.item.custom;

import com.example.examplemod.ModUtils;
import com.mojang.serialization.MapCodec;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;

import java.util.List;

public class MetalDetectorItem extends Item {

    public static final int SEARCH_DEPTH = 64;


    public MetalDetectorItem(Properties properties) {
        properties.durability(100);
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        ItemStack currentItem = context.getItemInHand();
        EquipmentSlot slot = context.getHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;

        if (player != null && player.getCooldowns().isOnCooldown(currentItem)) {
            return InteractionResult.FAIL;
        }

        if (!level.isClientSide()) {
            boolean foundBlock = false;

            for (int i = 1; i <= SEARCH_DEPTH; i++) {
                BlockState blockState = level.getBlockState(pos.below(i));
                if (isValuableBlock(blockState)) {
                    outputValuableBlockCoords(pos.below(i), player, blockState.getBlock());
                    ModUtils.sendMDPlingToPlayer(player, pos.getX(), pos.getY(), pos.getZ(), 20);
                    ModUtils.sendHappyVillagerEffectToLevel(level, pos.getX(), pos.getY(), pos.getZ());
                    foundBlock = true;

                    break;
                }

            }
            if (!foundBlock) {
                outputNoValuablesFound(player);
                ModUtils.sendSoundToPlayer(player, SoundEvents.NOTE_BLOCK_BASS, pos.getX(), pos.getY(), pos.getZ(), 20, 0.5f);
            }
            player.getCooldowns().addCooldown(currentItem, 20 * 3);
            currentItem.hurtAndBreak(1, player, slot);


        }





        return InteractionResult.SUCCESS;
    }

    private void outputNoValuablesFound(Player player) {
        player.sendSystemMessage(Component.literal("No ores found!"));
    }

    private void outputValuableBlockCoords(BlockPos below, Player player, Block block) {
        player.sendSystemMessage(Component.translatable("message.learning.found_ore", block.getName().withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD), below.toShortString()));

    }

    private boolean isValuableBlock(BlockState blockState) {
        return blockState.is(Tags.Blocks.ORES);
    }
}
