package subaraki.badbone.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

public interface PlayerHurtEvent {
    Event<PlayerHurtEvent> EVENT = EventFactory.createArrayBacked(PlayerHurtEvent.class,
            listeners -> (player, source, amt) -> {
                for (PlayerHurtEvent callback : listeners) {
                    callback.playerHurt(player, source, amt);
                }
            });

    void playerHurt(Player player, DamageSource source, float amt);
}
