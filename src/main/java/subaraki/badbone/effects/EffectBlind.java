package subaraki.badbone.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class EffectBlind extends MobEffect implements NotCurable {
    public EffectBlind() {
        super(MobEffectCategory.NEUTRAL, 0x423636);
    }

    public String getName() {
        return "effect.eye";
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }
}