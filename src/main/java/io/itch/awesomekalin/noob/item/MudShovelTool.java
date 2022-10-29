
package io.itch.awesomekalin.noob.item;

import net.minecraft.recipe.Ingredient;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.Item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class MudShovelTool {
	public static final ToolMaterial MUDSHOVEL_TOOL_MATERIAL = new ToolMaterial() {
		@Override
		public int getDurability() {
			return 10;
		}

		@Override
		public float getMiningSpeedMultiplier() {
			return 4F;
		}

		@Override
		public float getAttackDamage() {
			return -1.9F;
		}

		@Override
		public int getMiningLevel() {
			return 1;
		}

		@Override
		public int getEnchantability() {
			return 1;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.EMPTY;
		}
	};
	public static final Item INSTANCE = new ShovelItem(MUDSHOVEL_TOOL_MATERIAL, 0, (float) -3,
			(new FabricItemSettings().group(NoobTabItemGroup.get()))) {
	};
}
