package subaraki.badbone.mixin;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import subaraki.badbone.effects.NotCurable;

import java.util.Iterator;

@Mixin(MilkBucketItem.class)
public abstract class MixinMilkBucket {

    /**
     * @author StrikerRocker
     */
    @Overwrite
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide()) {
            Iterator<MobEffectInstance> itr = livingEntity.getActiveEffectsMap().values().iterator();
            while (itr.hasNext()) {
                MobEffectInstance effect = itr.next();
                if (!(effect.getEffect() instanceof NotCurable)) {
                    ((AccessorLivingEntity) livingEntity).invokeOnEffectRemoved(effect);
                    itr.remove();
                    ((AccessorLivingEntity) livingEntity).setEffectsDirty(true);
                }
            }
        }
        if (livingEntity instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer) livingEntity;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, itemStack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(((MilkBucketItem) (Object) this)));
        }

        if (livingEntity instanceof Player && !((Player) livingEntity).getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        return itemStack.isEmpty() ? new ItemStack(Items.BUCKET) : itemStack;
    }
}
