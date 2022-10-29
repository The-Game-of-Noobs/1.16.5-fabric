
package io.itch.awesomekalin.noob.item;

import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.entity.EquipmentSlot;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import io.itch.awesomekalin.noob.NoobMod;

public class NoobArmorArmorMaterial implements ArmorMaterial {
	private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
	private static final int[] PROTECTION_VALUES = new int[]{1, 2, 2, 1};
	public static final Item HELMET = new ArmorItem(new NoobArmorArmorMaterial(), EquipmentSlot.HEAD,
			new Item.Settings().group(NoobTabItemGroup.get()));
	public static final Item CHESTPLATE = new ArmorItem(new NoobArmorArmorMaterial(), EquipmentSlot.CHEST,
			new Item.Settings().group(NoobTabItemGroup.get()));
	public static final Item LEGGINGS = new ArmorItem(new NoobArmorArmorMaterial(), EquipmentSlot.LEGS,
			new Item.Settings().group(NoobTabItemGroup.get()));
	public static final Item BOOTS = new ArmorItem(new NoobArmorArmorMaterial(), EquipmentSlot.FEET,
			new Item.Settings().group(NoobTabItemGroup.get()));
	public int getDurability(EquipmentSlot equipmentSlot_1) {
		return BASE_DURABILITY[equipmentSlot_1.getEntitySlotId()] * 5;
	}

	public int getProtectionAmount(EquipmentSlot equipmentSlot_1) {
		return PROTECTION_VALUES[equipmentSlot_1.getEntitySlotId()];
	}

	public int getEnchantability() {
		return 3;
	}

	public SoundEvent getEquipSound() {
		return new SoundEvent(new Identifier(""));
	}

	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(NoobMod.NoobDust_ITEM);
	}

	@Environment(EnvType.CLIENT)
	public String getName() {
		return "noob";
	}

	public float getToughness() {
		return 0F;
	}

	public float getKnockbackResistance() {
		return 0f;
	}
}
