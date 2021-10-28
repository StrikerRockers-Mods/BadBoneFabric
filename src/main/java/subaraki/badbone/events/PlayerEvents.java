package subaraki.badbone.events;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import subaraki.badbone.mod.BadBone;
import subaraki.badbone.mod.ModConfig;

public class PlayerEvents {
    public static final ResourceLocation ADVANCEMENT = new ResourceLocation(BadBone.MODID, "eyesight");

    public static void registerEvents() {
        LivingEntityTickCallback.EVENT.register(livingEntity -> {
            if (livingEntity instanceof Player player) {
                if (player.level.isClientSide()) {
                    ClientEvent.clientPlayerUpdate(player);
                }
                if (player instanceof ServerPlayer serverPlayer) {
                    if (!serverPlayer.hasEffect(BadBone.BACK_HURT)) {
                        if (serverPlayer.getRandom().nextInt(ModConfig.frequencyHurt) == 0 || serverPlayer.getStats().getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) < 100) {
                            float fullInv = 4 * 9 * 64; //four rows of 9 slots with 64 items in
                            float totalCarrying = 0;
                            for (ItemStack stack : serverPlayer.getInventory().items) {
                                totalCarrying += stack.isEmpty() ? 0f : 64f * ((float) stack.getCount() / (float) stack.getMaxStackSize());
                            }
                            //the closer to 0 weight is, the more the player carries
                            //if the weight is taking up 2/3-ish from the inventory, then you can start having back pain
                            if (totalCarrying > fullInv * 0.65f) {
                                if (serverPlayer.getRandom().nextInt(ModConfig.chanceHurt) == 0) {
                                    serverPlayer.addEffect(new MobEffectInstance(BadBone.BACK_HURT, serverPlayer.getRandom().nextInt(20 * 60 * 2) + 20 * 15, 0, false, false, true));
                                    playHurtSound(serverPlayer);
                                }
                            }
                        }
                    }

                    if (serverPlayer.hasEffect(BadBone.ARTHRITIS)) {
                        if (serverPlayer.getMainHandItem().getAttributeModifiers(EquipmentSlot.MAINHAND).containsKey(Attributes.ATTACK_DAMAGE)) {
                            serverPlayer.drop(serverPlayer.getMainHandItem(), true);
                            serverPlayer.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                            playHurtSound(serverPlayer);
                        }
                        if (serverPlayer.getOffhandItem().getAttributeModifiers(EquipmentSlot.OFFHAND).containsKey(Attributes.ATTACK_DAMAGE)) {
                            serverPlayer.drop(serverPlayer.getOffhandItem(), true);
                            serverPlayer.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
                            playHurtSound(serverPlayer);
                        }
                    } else {
                        if (serverPlayer.isUsingItem() && serverPlayer.getRandom().nextInt(ModConfig.frequencyArthritis) == 0) {
                            serverPlayer.addEffect(new MobEffectInstance(BadBone.ARTHRITIS, serverPlayer.getRandom().nextInt(20 * 60 * 2) + 20 * 15, 0, false, false, true));
                        }
                    }

                    if (!serverPlayer.hasEffect(BadBone.BLIND)) {
                        if (serverPlayer.getRandom().nextInt(ModConfig.frequencyEyes) == 0) {
                            serverPlayer.addEffect(new MobEffectInstance(BadBone.BLIND, serverPlayer.getRandom().nextInt(20 * 60 * 15) + 20 * 60 * 15, 0, false, false, true));
                            serverPlayer.getAdvancements().award(serverPlayer.getServer().getAdvancements().getAdvancement(ADVANCEMENT), "eyesight");
                        }
                    }
                    if (serverPlayer.hasEffect(BadBone.CHRONO)) {
                        if (!serverPlayer.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(MobEffects.MOVEMENT_SLOWDOWN.getAttributeModifiers().get(Attributes.MOVEMENT_SPEED)))
                            serverPlayer.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(MobEffects.MOVEMENT_SLOWDOWN.getAttributeModifiers().get(Attributes.MOVEMENT_SPEED));
                    } else {
                        if (serverPlayer.getAttribute(Attributes.MOVEMENT_SPEED).hasModifier(MobEffects.MOVEMENT_SLOWDOWN.getAttributeModifiers().get(Attributes.MOVEMENT_SPEED)))
                            serverPlayer.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(MobEffects.MOVEMENT_SLOWDOWN.getAttributeModifiers().get(Attributes.MOVEMENT_SPEED));

                    }

                    BlockPos bubble = serverPlayer.blockPosition();
                    BlockPos magma = serverPlayer.getOnPos();
                    Level level = serverPlayer.level;
                    if (level.getBlockState(bubble).is(Blocks.BUBBLE_COLUMN) && level.getBlockState(magma).is(Blocks.MAGMA_BLOCK)) {
                        if (serverPlayer.hasEffect(BadBone.ARTHRITIS))
                            serverPlayer.removeEffect(BadBone.ARTHRITIS);
                        if (serverPlayer.hasEffect(BadBone.BACK_HURT))
                            serverPlayer.removeEffect(BadBone.BACK_HURT);
                        if (serverPlayer.hasEffect(BadBone.KNEE_HURT))
                            serverPlayer.removeEffect(BadBone.KNEE_HURT);
                        if (serverPlayer.hasEffect(BadBone.CHRONO))
                            serverPlayer.removeEffect(BadBone.CHRONO);
                    }
                }
            }
        });
        ItemCraftedEvent.EVENT.register((player, crafted) -> {
            if (crafted.getItem().equals(Items.CLOCK)) {
                player.addEffect(new MobEffectInstance(BadBone.CHRONO, 20 * 90, 0, false, false, true));
            }
        });
        ItemPickupEvent.EVENT.register((player, pickedUpStack) -> {
            if (pickedUpStack.getItem().equals(Items.CLOCK)) {
                player.addEffect(new MobEffectInstance(BadBone.CHRONO, 20 * 90, 0, false, false, true));
            }
        });
        PlayerHurtEvent.EVENT.register((player, source, amt) -> {
            if (player.fallDistance > 3 && source == DamageSource.FALL && player.getRandom().nextInt(ModConfig.frequencyKnee) == 0) {
                player.addEffect(new MobEffectInstance(BadBone.KNEE_HURT, player.getRandom().nextInt(20 * 15) * (int) player.fallDistance, 0, false, false, true));
                playHurtSound(player);
            }
        });
    }

    public static void playHurtSound(Player player) {
        player.playNotifySound(SoundEvents.BONE_BLOCK_BREAK, SoundSource.PLAYERS, 1f, player.getRandom().nextFloat() + 1f);
        player.playNotifySound(SoundEvents.PLAYER_HURT, SoundSource.PLAYERS, 1f, player.getRandom().nextFloat() + 1f);
    }
}
