package subaraki.badbone.mod;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import subaraki.badbone.effects.*;
import subaraki.badbone.events.PlayerEvents;
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
        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        PlayerEvents.registerEvents();
    }
}
