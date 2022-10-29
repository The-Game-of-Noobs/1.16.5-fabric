
package io.itch.awesomekalin.noob.entity.render;

import net.minecraft.util.Identifier;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;

import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

import io.itch.awesomekalin.noob.entity.TheNoobEntity;

public class TheNoobEntityRenderer extends MobEntityRenderer<TheNoobEntity, BipedEntityModel<TheNoobEntity>> {
	public TheNoobEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new BipedEntityModel<>(0), 0.5f);
		this.addFeature(new ArmorFeatureRenderer<>(this, new BipedEntityModel<>(0.5f), new BipedEntityModel<>(1)));
		this.addFeature(new HeldItemFeatureRenderer<>(this));
	}

	public static void clientInit() {
		EntityRendererRegistry.INSTANCE.register(TheNoobEntity.ENTITY, (dispatcher, context) -> new TheNoobEntityRenderer(dispatcher));
	}

	@Override
	public Identifier getTexture(TheNoobEntity entity) {
		return new Identifier("noob:textures/2022_08_17_noob-20735075.png");
	}
}
