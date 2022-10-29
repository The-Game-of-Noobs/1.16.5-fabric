
package io.itch.awesomekalin.noob.item;

import net.minecraft.world.World;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.LivingEntity;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import java.util.Map;
import java.util.HashMap;

import io.itch.awesomekalin.noob.procedures.MudAxeMobIsHitWithToolProcedure;

public class MudSwordTool {
	public static final ToolMaterial MUDSWORD_TOOL_MATERIAL = new ToolMaterial() {
		@Override
		public int getDurability() {
			return 10;
		}

		@Override
		public float getMiningSpeedMultiplier() {
			return 1F;
		}

		@Override
		public float getAttackDamage() {
			return -1.8F;
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
	public static final Item INSTANCE = new SwordItem(MUDSWORD_TOOL_MATERIAL, 0, (float) -3,
			(new FabricItemSettings().group(NoobTabItemGroup.get()))) {
		@Override
		public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
			super.postHit(stack, target, attacker);
			int x = (int) target.getPosX();
			int y = (int) target.getPosY();
			int z = (int) target.getPosZ();
			World world = target.world;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("itemstack", itemstack);
				MudAxeMobIsHitWithToolProcedure.executeProcedure($_dependencies);
			}
			return true;
		}
	};
}
