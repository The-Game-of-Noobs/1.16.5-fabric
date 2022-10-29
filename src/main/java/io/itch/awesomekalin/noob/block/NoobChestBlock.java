
package io.itch.awesomekalin.noob.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.World;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.BlockView;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.text.Text;
import net.minecraft.text.LiteralText;
import net.minecraft.structure.rule.RuleTestType;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.loot.context.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.Material;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.Block;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;

import java.util.stream.IntStream;
import java.util.function.Predicate;
import java.util.Random;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

import io.itch.awesomekalin.noob.screen.NoobChestGUIGui;
import io.itch.awesomekalin.noob.procedures.NoobChestOnBlockRightClickedProcedure;
import io.itch.awesomekalin.noob.NoobMod;

public class NoobChestBlock extends Block implements BlockEntityProvider {
	public NoobChestBlock() {
		super(FabricBlockSettings.of(Material.SPONGE).sounds(BlockSoundGroup.SLIME).strength(1f, 10f).luminance(0)
				.materialColor(MaterialColor.ORANGE_TERRACOTTA));
	}

	@Override
	public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDroppedStacks(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockHitResult hit) {
		super.onUse(state, world, pos, entity, hand, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getHitVec().x;
		double hitY = hit.getHitVec().y;
		double hitZ = hit.getHitVec().z;
		Direction direction = hit.getFace();
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			$_dependencies.put("x", x);
			$_dependencies.put("y", y);
			$_dependencies.put("z", z);
			$_dependencies.put("world", world);
			NoobChestOnBlockRightClickedProcedure.executeProcedure($_dependencies);
		}
		return ActionResult.SUCCESS;
	}

	@Override
	public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory) blockEntity : null;
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new CustomBlockEntity();
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
	public static class CustomBlockEntity extends LootableContainerBlockEntity implements ExtendedScreenHandlerFactory, SidedInventory {
		private DefaultedList<ItemStack> stacks = DefaultedList.<ItemStack>ofSize(size(), ItemStack.EMPTY);
		public CustomBlockEntity() {
			super(NoobMod.NoobChest_BLOCK_ENTITY);
		}

		@Override
		public int size() {
			return 2;
		}

		@Override
		public boolean isEmpty() {
			for (ItemStack itemstack : this.stacks)
				if (!itemstack.isEmpty())
					return false;
			return true;
		}

		@Override
		protected Text getContainerName() {
			return new LiteralText("noob_chest");
		}

		@Override
		public int getMaxCountPerStack() {
			return 3;
		}

		@Override
		public ScreenHandler createScreenHandler(int syncId, PlayerInventory inv) {
			return new NoobChestGUIGui.GuiContainerMod(syncId, inv, this);
		}

		@Override
		public Text getDisplayName() {
			return new LiteralText("Noob Chest");
		}

		@Override
		public DefaultedList<ItemStack> getInvStackList() {
			return stacks;
		}

		@Override
		protected void setInvStackList(DefaultedList<ItemStack> stacks) {
			this.stacks = stacks;
		}

		@Override
		public boolean isValid(int slot, ItemStack stack) {
			return true;
		}

		@Override
		public int[] getAvailableSlots(Direction side) {
			return IntStream.range(0, this.size()).toArray();
		}

		@Override
		public boolean canInsert(int index, ItemStack stack, @Nullable Direction direction) {
			return this.isValid(index, stack);
		}

		@Override
		public boolean canExtract(int index, ItemStack stack, Direction direction) {
			return true;
		}

		@Override
		public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
			buf.writeBlockPos(pos);
		}
	}

	private static class CustomRuleTest extends RuleTest {
		static final CustomRuleTest INSTANCE = new CustomRuleTest();
		static final com.mojang.serialization.Codec<CustomRuleTest> codec = com.mojang.serialization.Codec.unit(() -> INSTANCE);
		public boolean test(BlockState blockAt, Random random) {
			boolean blockCriteria = false;
			if (blockAt.getBlock() == Blocks.STONE.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.DIRT.getDefaultState().getBlock())
				blockCriteria = true;
			if (blockAt.getBlock() == Blocks.GRASS_BLOCK.getDefaultState().getBlock())
				blockCriteria = true;
			return blockCriteria;
		}

		protected RuleTestType<?> getType() {
			return Generation.CUSTOM_MATCH;
		}
	}

	public static class Generation {
		private static final RuleTestType<CustomRuleTest> CUSTOM_MATCH = Registry.register(Registry.RULE_TEST,
				new Identifier("noob", "noob_chest_match"), () -> CustomRuleTest.codec);
		public static final Feature<OreFeatureConfig> feature = Registry.register(Registry.FEATURE, new Identifier("noob", "noob_chest"),
				new OreFeature(OreFeatureConfig.CODEC) {
					@Override
					public boolean generate(StructureWorldAccess worldAccess, ChunkGenerator generator, Random rand, BlockPos pos,
							OreFeatureConfig config) {
						World world = worldAccess.toServerWorld();
						RegistryKey<World> dimensionType = world.getRegistryKey();
						boolean dimensionCriteria = false;
						if (dimensionType == World.OVERWORLD)
							dimensionCriteria = true;
						if (dimensionType == RegistryKey.of(Registry.DIMENSION, new Identifier("noob:noob_dim")))
							dimensionCriteria = true;
						if (!dimensionCriteria)
							return false;
						return super.generate(worldAccess, generator, rand, pos, config);
					}
				});
		private static final ConfiguredFeature<?, ?> CONFIG_FEATURE = feature
				.configure(new OreFeatureConfig(CustomRuleTest.INSTANCE, NoobMod.NoobChest_BLOCK.getDefaultState(), 16)).rangeOf(256)
				.spreadHorizontally().repeat(20);
		public static void init() {
			RegistryKey<ConfiguredFeature<?, ?>> configFeatKey = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
					new Identifier("noob", "noob_chest"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, configFeatKey.getValue(), CONFIG_FEATURE);
			Predicate<BiomeSelectionContext> biomeSelector = BiomeSelectors.all();
			BiomeModifications.addFeature(biomeSelector, GenerationStep.Feature.UNDERGROUND_ORES, configFeatKey);
		}
	}
}
