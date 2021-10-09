package subaraki.badbone.mod;

import net.fabricmc.api.ClientModInitializer;
import subaraki.badbone.events.ClientEvent;

public class ClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientEvent.event();
    }
}
