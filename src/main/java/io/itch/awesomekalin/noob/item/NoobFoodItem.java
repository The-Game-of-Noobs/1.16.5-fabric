
package io.itch.awesomekalin.noob.item;

import net.minecraft.world.World;
import net.minecraft.util.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.FoodComponent;
import net.minecraft.entity.LivingEntity;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import java.util.Map;
import java.util.HashMap;

public class NoobFoodItem extends Item {
	public NoobFoodItem() {
		super(new FabricItemSettings().group(NoobTabItemGroup.get()).maxCount(64)
				.food((new FoodComponent.Builder()).hunger(1).saturationModifier(0f).build()));
	}

	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}

	@Override
	public net.minecraft.sound.SoundEvent getEatSound() {
		return net.minecraft.sound.SoundEvents.ENTITY_GENERIC_DRINK;
	}

	@Override
	public ItemStack finishUsing(ItemStack itemStack, World world, LivingEntity entity) {
		int x = (int) entity.getX();
		int y = (int) entity.getY();
		int z = (int) entity.getZ();
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			$_dependencies.put("x", x);
			$_dependencies.put("y", y);
			$_dependencies.put("z", z);
			$_dependencies.put("world", world);
			NoobFoodFoodEatenProcedure.executeProcedure($_dependencies);
		}
		return super.finishUsing(itemStack, world, entity);
	}
}
