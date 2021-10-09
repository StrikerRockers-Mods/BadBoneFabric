package subaraki.badbone.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import subaraki.badbone.events.ItemCraftedEvent;

@Mixin(ResultSlot.class)
public class MixinResultSlot {
    @Shadow @Final private Player player;

    @Inject(method = "checkTakeAchievements",at=@At(value = "INVOKE" ,target = "Lnet/minecraft/world/item/ItemStack;onCraftedBy(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/player/Player;I)V"))
    public void craftingEvent(ItemStack itemStack, CallbackInfo ci){
        ItemCraftedEvent.EVENT.invoker().itemCrafted(this.player,itemStack);
    }
}
