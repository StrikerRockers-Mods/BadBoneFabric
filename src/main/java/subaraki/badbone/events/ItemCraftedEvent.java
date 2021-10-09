package subaraki.badbone.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface ItemCraftedEvent {
    Event<ItemCraftedEvent> EVENT = EventFactory.createArrayBacked(ItemCraftedEvent.class,
            listeners -> ((player, crafted) -> {
                for (ItemCraftedEvent callback : listeners) {
                    callback.itemCrafted(player, crafted);
                }
            }));

    void itemCrafted(Player player, ItemStack crafted);
}
