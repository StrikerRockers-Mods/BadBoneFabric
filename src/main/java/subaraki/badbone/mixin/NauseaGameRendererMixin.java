package subaraki.badbone.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import subaraki.badbone.mod.BadBone;

@Mixin(GameRenderer.class)
public class NauseaGameRendererMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z"))
    public boolean hasEffectRender(LocalPlayer instance, MobEffect mobEffect) {
        return instance.hasEffect(MobEffects.CONFUSION) || instance.hasEffect(BadBone.CHRONO);
    }

    @Redirect(method = "renderLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z"))
    public boolean hasEffectRenderLevel(LocalPlayer instance, MobEffect mobEffect) {
        return instance.hasEffect(MobEffects.CONFUSION) || instance.hasEffect(BadBone.CHRONO);
    }
}
