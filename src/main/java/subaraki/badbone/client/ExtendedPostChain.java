package subaraki.badbone.client;

import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import subaraki.badbone.mixin.AccessorPostChain;

import java.io.IOException;

public class ExtendedPostChain extends PostChain {
    public ExtendedPostChain(TextureManager textureManager, ResourceManager resourceManager, RenderTarget renderTarget, ResourceLocation resourceLocation) throws IOException, JsonSyntaxException {
        super(textureManager, resourceManager, renderTarget, resourceLocation);
    }

    public ExtendedPostChain(String domain, String name) throws JsonSyntaxException, IOException {
        this(Minecraft.getInstance().getTextureManager(), Minecraft.getInstance().getResourceManager(), Minecraft.getInstance().getMainRenderTarget(), new ResourceLocation(domain, "shaders/post/" + name + ".json"));
        this.resize(Minecraft.getInstance().getWindow().getWidth(), Minecraft.getInstance().getWindow().getHeight());
    }

    @Override
    public void process(float frameTime) {
        Window w = Minecraft.getInstance().getWindow();
        AccessorPostChain chain = (AccessorPostChain) this;
        if (chain.getScreenWidth() != w.getWidth() || chain.getScreenHeight() != w.getHeight()) {
            this.resize(w.getWidth(), w.getHeight());
        }
        super.process(frameTime);
    }
}
