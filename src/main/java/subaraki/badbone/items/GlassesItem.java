package subaraki.badbone.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class GlassesItem extends Item {
    private static final Properties PROPERTIES = new FabricItemSettings().equipmentSlot(stack -> EquipmentSlot.HEAD).tab(CreativeModeTab.TAB_TOOLS).stacksTo(1);

    public GlassesItem() {
        super(PROPERTIES);
    }
}
