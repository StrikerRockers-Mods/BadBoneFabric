package subaraki.badbone.events;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import subaraki.badbone.items.GlassesItem;
import subaraki.badbone.mod.BadBone;

public class ClientEvent {

    public static void clientPlayerUpdate(Player player) {
        if (player instanceof LocalPlayer localPlayer) {
            if (localPlayer.hasEffect(BadBone.BACK_HURT) || localPlayer.hasEffect(BadBone.KNEE_HURT)) {
                localPlayer.input.shiftKeyDown = true;
                localPlayer.setShiftKeyDown(true);
            }
        }
    }
}
