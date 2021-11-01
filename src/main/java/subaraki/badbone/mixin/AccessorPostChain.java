package subaraki.badbone.mixin;

import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.PostPass;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(PostChain.class)
public interface AccessorPostChain {

    @Accessor("screenHeight")
    int getScreenHeight();

    @Accessor("screenWidth")
    int getScreenWidth();

    @Accessor("passes")
    List<PostPass> getPasses();
}
