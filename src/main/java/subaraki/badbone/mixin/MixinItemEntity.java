package subaraki.badbone.mixin;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import subaraki.badbone.events.ItemPickupEvent;

@Mixin(ItemEntity.class)
public class MixinItemEntity {
    @Inject(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;onItemPickup(Lnet/minecraft/world/entity/item/ItemEntity;)V"))
    public void fireEvent(Player player, CallbackInfo ci) {
        ItemPickupEvent.EVENT.invoker().itemPick(player, ((ItemEntity) (Object) this).getItem());
    }
}
