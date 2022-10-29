
package io.itch.awesomekalin.noob.item;

import net.minecraft.recipe.Ingredient;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import io.itch.awesomekalin.noob.NoobMod;

public class NoobSwordTool {
	public static final ToolMaterial NOOBSWORD_TOOL_MATERIAL = new ToolMaterial() {
		@Override
		public int getDurability() {
			return 46;
		}

		@Override
		public float getMiningSpeedMultiplier() {
			return 3F;
		}

		@Override
		public float getAttackDamage() {
			return -1F;
		}

		@Override
		public int getMiningLevel() {
			return 1;
		}

		@Override
		public int getEnchantability() {
			return 4;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.ofItems(NoobMod.NoobDust_ITEM);
		}
	};
	public static final Item INSTANCE = new SwordItem(NOOBSWORD_TOOL_MATERIAL, 0, (float) -3,
			(new FabricItemSettings().group(NoobTabItemGroup.get()))) {
	};
}
