package subaraki.badbone.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface ItemPickupEvent {
    Event<ItemPickupEvent> EVENT = EventFactory.createArrayBacked(ItemPickupEvent.class,
            listeners -> (player, pickedUpStack) -> {
                for (ItemPickupEvent callback : listeners) {
                    callback.itemPick(player, pickedUpStack);
                }
            });

    void itemPick(Player player, ItemStack pickedUpStack);
}
