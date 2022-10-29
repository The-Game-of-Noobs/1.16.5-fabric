
package io.itch.awesomekalin.noob.item;

import net.minecraft.util.Identifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;

import io.itch.awesomekalin.noob.NoobMod;

public final class NoobTabItemGroup {
	public static ItemGroup get() {
		return ITEM_GROUP;
	}
	private static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier("noob", "noob_tab")).icon(() -> {
		return new ItemStack(NoobMod.NoobChest_ITEM);
	}).build();
}
