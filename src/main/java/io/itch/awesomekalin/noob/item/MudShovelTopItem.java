
package io.itch.awesomekalin.noob.item;

import net.minecraft.util.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class MudShovelTopItem extends Item {
	public MudShovelTopItem() {
		super(new FabricItemSettings().group(NoobTabItemGroup.get()).maxCount(1).rarity(Rarity.COMMON));
	}

	@Override
	public int getMaxUseTime(ItemStack itemstack) {
		return 0;
	}

	@Override
	public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
		return (float) (1F);
	}

	@Override
	public int getEnchantability() {
		return 0;
	}
}
