package subaraki.badbone.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class EffectBackpain extends MobEffect {
    public EffectBackpain() {
        super(MobEffectCategory.NEUTRAL, 0x423636);
    }

    public String getName() {
        return "effect.backpain";
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }
}