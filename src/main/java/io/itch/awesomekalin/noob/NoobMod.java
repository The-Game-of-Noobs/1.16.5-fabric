/*
 *    MCreator note:
 *
 *    If you lock base mod element files, you can edit this file and the proxy files
 *    and they won't get overwritten. If you change your mod package or modid, you
 *    need to apply these changes to this file MANUALLY.
 *
 *
 *    If you do not lock base mod element files in Workspace settings, this file
 *    will be REGENERATED on each build.
 *
 */
package io.itch.awesomekalin.noob;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraft.world.biome.Biome;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.Block;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.api.ModInitializer;

import io.itch.awesomekalin.noob.world.biomes.NoobBioBiome;
import io.itch.awesomekalin.noob.screen.NoobChestGUIGui;
import io.itch.awesomekalin.noob.procedures.NoobChestOnBlockRightClickedProcedure;
import io.itch.awesomekalin.noob.procedures.MudAxeMobIsHitWithToolProcedure;
import io.itch.awesomekalin.noob.item.SalivaItem;
import io.itch.awesomekalin.noob.item.NoobTabItemGroup;
import io.itch.awesomekalin.noob.item.NoobSwordTool;
import io.itch.awesomekalin.noob.item.NoobShovelTool;
import io.itch.awesomekalin.noob.item.NoobPickaxeTool;
import io.itch.awesomekalin.noob.item.NoobHoeTool;
import io.itch.awesomekalin.noob.item.NoobDustItem;
import io.itch.awesomekalin.noob.item.NoobAxeTool;
import io.itch.awesomekalin.noob.item.NoobArmorArmorMaterial;
import io.itch.awesomekalin.noob.item.MudSwordTool;
import io.itch.awesomekalin.noob.item.MudShovelTopItem;
import io.itch.awesomekalin.noob.item.MudShovelTool;
import io.itch.awesomekalin.noob.item.MudShovelBottomItem;
import io.itch.awesomekalin.noob.item.MudPickaxeTool;
import io.itch.awesomekalin.noob.item.MudHoeTool;
import io.itch.awesomekalin.noob.item.MudAxeTool;
import io.itch.awesomekalin.noob.item.DirtArmourArmorArmorMaterial;
import io.itch.awesomekalin.noob.item.CreativeGauntletItem;
import io.itch.awesomekalin.noob.entity.TheNoobEntity;
import io.itch.awesomekalin.noob.client.gui.screen.NoobChestGUIGuiWindow;
import io.itch.awesomekalin.noob.block.NoobOreBlock;
import io.itch.awesomekalin.noob.block.NoobChestBlock;
import io.itch.awesomekalin.noob.block.NoobBlockBlock;

public class NoobMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final Item NoobDust_ITEM = Registry.register(Registry.ITEM, id("noob_dust"), new NoobDustItem());
	public static final Item MudShovelTop_ITEM = Registry.register(Registry.ITEM, id("mud_shovel_top"), new MudShovelTopItem());
	public static final Item MudShovelBottom_ITEM = Registry.register(Registry.ITEM, id("mud_shovel_bottom"), new MudShovelBottomItem());
	public static final Item Saliva_ITEM = Registry.register(Registry.ITEM, id("saliva"), new SalivaItem());
	public static final Item CreativeGauntlet_ITEM = Registry.register(Registry.ITEM, id("creative_gauntlet"), new CreativeGauntletItem());
	public static final ItemGroup NoobTab = NoobTabItemGroup.get();
	public static final Block NoobOre_BLOCK = Registry.register(Registry.BLOCK, id("noob_ore"), new NoobOreBlock());
	public static final BlockItem NoobOre_ITEM = Registry.register(Registry.ITEM, id("noob_ore"),
			new BlockItem(NoobOre_BLOCK, new Item.Settings().group(NoobTabItemGroup.get())));
	public static final Block NoobBlock_BLOCK = Registry.register(Registry.BLOCK, id("noob_block"), new NoobBlockBlock());
	public static final BlockItem NoobBlock_ITEM = Registry.register(Registry.ITEM, id("noob_block"),
			new BlockItem(NoobBlock_BLOCK, new Item.Settings().group(NoobTabItemGroup.get())));
	public static final Block NoobChest_BLOCK = Registry.register(Registry.BLOCK, id("noob_chest"), new NoobChestBlock());
	public static final BlockItem NoobChest_ITEM = Registry.register(Registry.ITEM, id("noob_chest"),
			new BlockItem(NoobChest_BLOCK, new Item.Settings().group(NoobTabItemGroup.get())));
	public static final BlockEntityType<NoobChestBlock.CustomBlockEntity> NoobChest_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
			id("noob_chest"), BlockEntityType.Builder.create(NoobChestBlock.CustomBlockEntity::new, NoobChest_BLOCK).build(null));
	public static final Item NoobArmor_HELMET = Registry.register(Registry.ITEM, id("noob_armor_helmet"), NoobArmorArmorMaterial.HELMET);
	public static final Item NoobArmor_CHESTPLATE = Registry.register(Registry.ITEM, id("noob_armor_chestplate"), NoobArmorArmorMaterial.CHESTPLATE);
	public static final Item NoobArmor_LEGGINGS = Registry.register(Registry.ITEM, id("noob_armor_leggings"), NoobArmorArmorMaterial.LEGGINGS);
	public static final Item NoobArmor_BOOTS = Registry.register(Registry.ITEM, id("noob_armor_boots"), NoobArmorArmorMaterial.BOOTS);
	public static final Item DirtArmourArmor_HELMET = Registry.register(Registry.ITEM, id("dirt_armour_armor_helmet"),
			DirtArmourArmorArmorMaterial.HELMET);
	public static final Item DirtArmourArmor_CHESTPLATE = Registry.register(Registry.ITEM, id("dirt_armour_armor_chestplate"),
			DirtArmourArmorArmorMaterial.CHESTPLATE);
	public static final Item DirtArmourArmor_LEGGINGS = Registry.register(Registry.ITEM, id("dirt_armour_armor_leggings"),
			DirtArmourArmorArmorMaterial.LEGGINGS);
	public static final Item DirtArmourArmor_BOOTS = Registry.register(Registry.ITEM, id("dirt_armour_armor_boots"),
			DirtArmourArmorArmorMaterial.BOOTS);
	public static final Item MudAxe_ITEM = Registry.register(Registry.ITEM, id("mud_axe"), MudAxeTool.INSTANCE);
	public static final Item MudSword_ITEM = Registry.register(Registry.ITEM, id("mud_sword"), MudSwordTool.INSTANCE);
	public static final Item MudPickaxe_ITEM = Registry.register(Registry.ITEM, id("mud_pickaxe"), MudPickaxeTool.INSTANCE);
	public static final Item NoobPickaxe_ITEM = Registry.register(Registry.ITEM, id("noob_pickaxe"), NoobPickaxeTool.INSTANCE);
	public static final Item NoobAxe_ITEM = Registry.register(Registry.ITEM, id("noob_axe"), NoobAxeTool.INSTANCE);
	public static final Item NoobSword_ITEM = Registry.register(Registry.ITEM, id("noob_sword"), NoobSwordTool.INSTANCE);
	public static final Item NoobShovel_ITEM = Registry.register(Registry.ITEM, id("noob_shovel"), NoobShovelTool.INSTANCE);
	public static final Item NoobHoe_ITEM = Registry.register(Registry.ITEM, id("noob_hoe"), NoobHoeTool.INSTANCE);
	public static final Item MudShovel_ITEM = Registry.register(Registry.ITEM, id("mud_shovel"), MudShovelTool.INSTANCE);
	public static final Item MudHoe_ITEM = Registry.register(Registry.ITEM, id("mud_hoe"), MudHoeTool.INSTANCE);
	public static final RegistryKey<Biome> NoobBio_KEY = RegistryKey.of(Registry.BIOME_KEY, id("noob_bio"));
	public static final ScreenHandlerType<NoobChestGUIGui.GuiContainerMod> NoobChestGUIScreenType = ScreenHandlerRegistry
			.registerExtended(id("noob_chest_gui"), NoobChestGUIGui.GuiContainerMod::new);
	@Override
	public void onInitialize() {
		LOGGER.info("Initializing NoobMod");
		NoobOreBlock.Generation.init();
		NoobChestBlock.Generation.init();
		new NoobChestOnBlockRightClickedProcedure();
		new NoobFoodFoodEatenProcedure();
		new MudAxeMobIsHitWithToolProcedure();
		new CreativeGauntletRightClickedProcedure();
		NoobBioBiome.init();
		TheNoobEntity.init();
		NoobChestGUIGuiWindow.screenInit();
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
		});
	}

	public static final Identifier id(String s) {
		return new Identifier("noob", s);
	}
}
