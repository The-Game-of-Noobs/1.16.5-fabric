
package io.itch.awesomekalin.noob.item;

import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.entity.EquipmentSlot;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class DirtArmourArmorArmorMaterial implements ArmorMaterial {
	private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
	private static final int[] PROTECTION_VALUES = new int[]{0, 1, 1, 0};
	public static final Item HELMET = new ArmorItem(new DirtArmourArmorArmorMaterial(), EquipmentSlot.HEAD,
			new Item.Settings().group(NoobTabItemGroup.get()));
	public static final Item CHESTPLATE = new ArmorItem(new DirtArmourArmorArmorMaterial(), EquipmentSlot.CHEST,
			new Item.Settings().group(NoobTabItemGroup.get()));
	public static final Item LEGGINGS = new ArmorItem(new DirtArmourArmorArmorMaterial(), EquipmentSlot.LEGS,
			new Item.Settings().group(NoobTabItemGroup.get()));
	public static final Item BOOTS = new ArmorItem(new DirtArmourArmorArmorMaterial(), EquipmentSlot.FEET,
			new Item.Settings().group(NoobTabItemGroup.get()));
	public int getDurability(EquipmentSlot equipmentSlot_1) {
		return BASE_DURABILITY[equipmentSlot_1.getEntitySlotId()] * 2;
	}

	public int getProtectionAmount(EquipmentSlot equipmentSlot_1) {
		return PROTECTION_VALUES[equipmentSlot_1.getEntitySlotId()];
	}

	public int getEnchantability() {
		return 1;
	}

	public SoundEvent getEquipSound() {
		return new SoundEvent(new Identifier(""));
	}

	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(Items.DIRT);
	}

	@Environment(EnvType.CLIENT)
	public String getName() {
		return "dirtarmour_";
	}

	public float getToughness() {
		return 0F;
	}

	public float getKnockbackResistance() {
		return 0f;
	}
}
