package subaraki.badbone.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class EffectChronophobia extends MobEffect {
    public EffectChronophobia() {
        super(MobEffectCategory.NEUTRAL, 0x423636);
    }

    public String getName() {
        return "effect.chrono";
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }
}