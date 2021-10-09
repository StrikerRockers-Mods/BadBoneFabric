package subaraki.badbone.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class EffectArthritis extends MobEffect {
    public EffectArthritis() {
        super(MobEffectCategory.NEUTRAL, 0x423636);
    }

    public String getName() {
        return "effect.arthritis";
    }

    @Override
    public boolean isBeneficial() {
        return false;
    }
}