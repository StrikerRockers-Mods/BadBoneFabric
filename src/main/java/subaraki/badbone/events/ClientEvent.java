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
    private static final String SHADER_NAME = "badbone:shaders/post/blur.json";
    private static final ResourceLocation SHADER = new ResourceLocation(SHADER_NAME);
    private static final ResourceLocation NAUSEA_LOCATION = new ResourceLocation("textures/misc/nausea.png");

    public static void clientPlayerUpdate(Player player) {
        if (player instanceof LocalPlayer localPlayer) {
            if (localPlayer.hasEffect(BadBone.BACK_HURT) || localPlayer.hasEffect(BadBone.KNEE_HURT)) {
                localPlayer.input.shiftKeyDown = true;
                localPlayer.setShiftKeyDown(true);
            }
        }
    }

    public static void event(){
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
            Player player = Minecraft.getInstance().player;
            if (player.hasEffect(BadBone.BLIND) && !(player.getInventory().getArmor(3).getItem() instanceof GlassesItem)) {
                PostChain shader = Minecraft.getInstance().gameRenderer.currentEffect();
                if (shader == null || !shader.getName().equals(SHADER_NAME)) {
                    Minecraft.getInstance().gameRenderer.loadEffect(SHADER);
                }
            } else {
                PostChain shader = Minecraft.getInstance().gameRenderer.currentEffect();
                if (shader != null && shader.getName().equals(SHADER_NAME))
                    Minecraft.getInstance().gameRenderer.shutdownEffect();
            }
        });
    }
}
