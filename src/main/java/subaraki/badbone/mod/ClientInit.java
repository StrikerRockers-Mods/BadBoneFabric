package subaraki.badbone.mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;
import subaraki.badbone.client.BadBoneShaders;
import subaraki.badbone.events.ClientEvent;

public class ClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(BadBoneShaders.INSTANCE);
    }
}
