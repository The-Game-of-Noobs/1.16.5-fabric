/*
*    MCreator note:
*
*    If you lock base mod element files, you can edit this file and the proxy files
*    and they won't get overwritten. If you change your mod package or modid, you
*    need to apply these changes to this file MANUALLY.
*
*
*    If you do not lock base mod element files in Workspace settings, this file
*    will be REGENERATED on each build.
*
*/
package io.itch.awesomekalin.noob;

import net.minecraft.client.render.RenderLayer;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ClientModInitializer;

import io.itch.awesomekalin.noob.entity.render.TheNoobEntityRenderer;
import io.itch.awesomekalin.noob.client.gui.screen.NoobChestGUIGuiWindow;

@Environment(EnvType.CLIENT)
public class ClientInit implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(NoobMod.NoobOre_BLOCK, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(NoobMod.NoobBlock_BLOCK, RenderLayer.getCutoutMipped());
		BlockRenderLayerMap.INSTANCE.putBlock(NoobMod.NoobChest_BLOCK, RenderLayer.getCutoutMipped());
		TheNoobEntityRenderer.clientInit();
		HudRenderCallback.EVENT.register((matrices, tickDelta) -> {
		});
		ScreenRegistry.register(NoobMod.NoobChestGUIScreenType, NoobChestGUIGuiWindow::new);
		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
		});
	}
}
