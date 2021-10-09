package subaraki.badbone.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class EffectWeakKnees extends MobEffect {
    public EffectWeakKnees() {
        super(MobEffectCategory.NEUTRAL, 0x423636);
    }

    public String getName() {
        return "effect.kneepain";
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }
}