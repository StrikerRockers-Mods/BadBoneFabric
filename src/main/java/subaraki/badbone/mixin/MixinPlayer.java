package subaraki.badbone.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import subaraki.badbone.events.PlayerHurtEvent;

@Mixin(Player.class)
public class MixinPlayer {
    @Inject(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isDeadOrDying()Z"))
    public void event(DamageSource damageSource, float f, CallbackInfoReturnable<Boolean> cir) {
        PlayerHurtEvent.EVENT.invoker().playerHurt((Player) (Object) this, damageSource, f);
    }
}
