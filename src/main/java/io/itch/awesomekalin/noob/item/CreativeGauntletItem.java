
package io.itch.awesomekalin.noob.item;

import net.minecraft.world.World;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Rarity;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.BlockState;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import java.util.Map;
import java.util.HashMap;

public class CreativeGauntletItem extends Item {
	public CreativeGauntletItem() {
		super(new FabricItemSettings().group(NoobTabItemGroup.get()).maxCount(1).rarity(Rarity.EPIC));
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

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		PlayerEntity entity = context.getPlayer();
		Direction direction = context.getSide();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		ItemStack itemstack = context.getItem();
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			$_dependencies.put("world", world);
			CreativeGauntletRightClickedProcedure.executeProcedure($_dependencies);
		}
		return ActionResult.PASS;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
		TypedActionResult<ItemStack> retval = super.use(world, entity, hand);
		ItemStack itemstack = retval.getValue();
		double x = entity.getPos().getX();
		double y = entity.getPos().getY();
		double z = entity.getPos().getZ();
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			$_dependencies.put("world", world);
			CreativeGauntletRightClickedProcedure.executeProcedure($_dependencies);
		}
		return super.use(world, entity, hand);
	}
}
