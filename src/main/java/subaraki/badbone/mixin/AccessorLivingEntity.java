package subaraki.badbone.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface AccessorLivingEntity {
    @Accessor("effectsDirty")
    void setEffectsDirty(boolean dirty);

    @Invoker
    void invokeOnEffectRemoved(MobEffectInstance pEffect);
}
