package io.itch.awesomekalin.noob.procedures;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.text.Text;
import net.minecraft.text.LiteralText;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;

import java.util.Map;

import io.netty.buffer.Unpooled;

import io.itch.awesomekalin.noob.screen.NoobChestGUIGui;

public class NoobChestOnBlockRightClickedProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure NoobChestOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure NoobChestOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure NoobChestOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure NoobChestOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure NoobChestOnBlockRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = (double) dependencies.get("x");
		double y = (double) dependencies.get("y");
		double z = (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		{
			Entity _ent = entity;
			if (_ent instanceof ServerPlayerEntity) {
				((ServerPlayerEntity) _ent).openHandledScreen(new ExtendedScreenHandlerFactory() {
					BlockPos _pos = new BlockPos((int) x, (int) y, (int) z);
					@Override
					public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
						buf.writeBlockPos(_pos);
					}

					@Override
					public Text getDisplayName() {
						return new LiteralText("NoobChestGUI");
					}

					@Override
					public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
						return new NoobChestGUIGui.GuiContainerMod(syncId, inv, new PacketByteBuf(Unpooled.buffer()).writeBlockPos(_pos));
					}
				});
			}
		}
	}
}
