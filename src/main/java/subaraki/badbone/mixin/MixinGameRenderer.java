package subaraki.badbone.mixin;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PostChain;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import subaraki.badbone.events.ClientEvent;

/**
 * Responsible for f4 not disabling Bad Eyes effect
 */

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {
    @Shadow
    public abstract PostChain currentEffect();

    /**
     * @author Subaraki
     */
    @Overwrite
    public void togglePostEffect() {
        if (this.currentEffect() != null && this.currentEffect().getName() != null)
            if (!this.currentEffect().getName().equals(ClientEvent.SHADER.toString())) {
                ((EffectField) this).setActive(!((EffectField) this).getEffectActive());
            }
    }

    @Mixin(GameRenderer.class)
    public interface EffectField {
        @Accessor("effectActive")
        void setActive(boolean isActive);

        @Accessor("effectActive")
        boolean getEffectActive();
    }
}
