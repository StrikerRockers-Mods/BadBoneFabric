package subaraki.badbone.client;

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import subaraki.badbone.mod.BadBone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BadBoneShaders implements ResourceManagerReloadListener, IdentifiableResourceReloadListener {
    public static final BadBoneShaders INSTANCE = new BadBoneShaders();
    protected final List<ExtendedPostChain> shaders = new ArrayList<>(2);
    protected ExtendedPostChain blur;

    private BadBoneShaders() {
    }

    @Override
    public ResourceLocation getFabricId() {
        return new ResourceLocation(BadBone.MODID);
    }

    @Override
    public void onResourceManagerReload(ResourceManager manager) {
        this.clear();
        try {
            blur = this.add(new ExtendedPostChain(BadBone.MODID, "blur"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        this.shaders.forEach(PostChain::close);
        this.shaders.clear();
    }

    public ExtendedPostChain add(ExtendedPostChain shader) {
        this.shaders.add(shader);
        return shader;
    }

    public ExtendedPostChain getBlur() {
        return blur;
    }
}
