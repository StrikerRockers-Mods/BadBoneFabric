package subaraki.badbone.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GlassesItem extends Item {
    private static final Properties PROPERTIES = new FabricItemSettings().equipmentSlot(stack -> EquipmentSlot.HEAD).tab(CreativeModeTab.TAB_TOOLS).stacksTo(1);

    public GlassesItem() {
        super(PROPERTIES);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack glasses = player.getItemInHand(hand);
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        if (helmet.isEmpty()) {
            player.setItemSlot(EquipmentSlot.HEAD, glasses.copy());
            if (!level.isClientSide()) {
                player.awardStat(Stats.ITEM_USED.get(this));
            }

            glasses.setCount(0);
            return InteractionResultHolder.sidedSuccess(glasses, level.isClientSide());
        } else {
            return InteractionResultHolder.fail(glasses);
        }
    }
}
