package subaraki.badbone.mod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import subaraki.badbone.effects.*;
import subaraki.badbone.events.*;
import subaraki.badbone.items.GlassesItem;

public class BadBone implements ModInitializer {
    public static final String MODID = "badbone";
    public static final Item GLASSES = Registry.register(Registry.ITEM, new ResourceLocation(MODID, "glasses"), new GlassesItem());
    public static final MobEffect CHRONO = Registry.register(Registry.MOB_EFFECT, new ResourceLocation(MODID, "chrono"), new EffectChronophobia());
    public static final MobEffect BLIND = Registry.register(Registry.MOB_EFFECT, new ResourceLocation(MODID, "eye"), new EffectBlind());
    public static final MobEffect ARTHRITIS = Registry.register(Registry.MOB_EFFECT, new ResourceLocation(MODID, "arthritis"), new EffectArthritis());
    public static final MobEffect KNEE_HURT = Registry.register(Registry.MOB_EFFECT, new ResourceLocation(MODID, "knee"), new EffectWeakKnees());
    public static final MobEffect BACK_HURT = Registry.register(Registry.MOB_EFFECT, new ResourceLocation(MODID, "hurt"), new EffectBackpain());
    public static final Logger LOG = LogManager.getLogger();

    @Override
    public void onInitialize() {
        PlayerEvents.registerEvents();
    }
}
