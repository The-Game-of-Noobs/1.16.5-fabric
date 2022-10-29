package io.itch.awesomekalin.noob.procedures;

import net.minecraft.text.LiteralText;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;

import java.util.Map;

public class MudAxeMobIsHitWithToolProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MudAxeMobIsHitWithTool!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure MudAxeMobIsHitWithTool!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		entity.setCustomName(new LiteralText("Noobed"));
		((itemstack)).setCustomName(new LiteralText("HOW DARE YOU!"));
		{
			Entity _ent = entity;
			if (!_ent.world.isClient()) {
				_ent.world.getServer().getCommandManager().execute(_ent.getCommandSource().withSilent().withLevel(4), "effect @p poison 5 5");
			}
		}
	}
}
