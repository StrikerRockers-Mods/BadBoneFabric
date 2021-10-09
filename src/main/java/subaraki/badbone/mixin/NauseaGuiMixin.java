package subaraki.badbone.mixin;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import subaraki.badbone.mod.BadBone;

@Mixin(Gui.class)
public class NauseaGuiMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z"))
    private boolean hasEffect(LocalPlayer instance, MobEffect mobEffect) {
        return instance.hasEffect(MobEffects.CONFUSION) || instance.hasEffect(BadBone.CHRONO);
    }
}
