
package io.itch.awesomekalin.noob.world.biomes;

import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.Biome;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.block.Blocks;

import io.itch.awesomekalin.noob.NoobMod;

public class NoobBioBiome {
	private static Biome theBiome;
	private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> SURFACE_BUILDER = SurfaceBuilder.DEFAULT
			.withConfig(new TernarySurfaceConfig(Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState()));
	public static void init() {
		Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, NoobMod.NoobBio_KEY.getValue(), SURFACE_BUILDER);
		BiomeEffects effects = new BiomeEffects.Builder().fogColor(-5916161).waterColor(-14329397).waterFogColor(-14329397).skyColor(-5916161)
				.grassColor(-13261999).foliageColor(-13261999).build();
		GenerationSettings.Builder genSettingsBuilder = new GenerationSettings.Builder();
		genSettingsBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, Feature.TREE
				.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.DIRT.getDefaultState()),
						new SimpleBlockStateProvider(Blocks.DIRT.getDefaultState()),
						new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3), new StraightTrunkPlacer(5, 2, 0),
						new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build()))
				.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP)
				.decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(4, 0.1f, 1))));
		DefaultBiomeFeatures.addLandCarvers(genSettingsBuilder);
		DefaultBiomeFeatures.addDungeons(genSettingsBuilder);
		DefaultBiomeFeatures.addDefaultOres(genSettingsBuilder);
		DefaultBiomeFeatures.addDefaultLakes(genSettingsBuilder);
		DefaultBiomeFeatures.addDefaultFlowers(genSettingsBuilder);
		DefaultBiomeFeatures.addDefaultGrass(genSettingsBuilder);
		genSettingsBuilder.structureFeature(ConfiguredStructureFeatures.STRONGHOLD);
		genSettingsBuilder.structureFeature(ConfiguredStructureFeatures.MINESHAFT);
		genSettingsBuilder.structureFeature(ConfiguredStructureFeatures.PILLAGER_OUTPOST);
		genSettingsBuilder.surfaceBuilder(SURFACE_BUILDER);
		SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
		Biome.Builder biomeBuilder = new Biome.Builder();
		biomeBuilder.effects(effects);
		biomeBuilder.generationSettings(genSettingsBuilder.build());
		biomeBuilder.spawnSettings(spawnBuilder.build());
		biomeBuilder.temperatureModifier(Biome.TemperatureModifier.NONE);
		biomeBuilder.temperature(0.5F);
		biomeBuilder.downfall(1F);
		biomeBuilder.depth(0.1F);
		biomeBuilder.scale(0.2F);
		biomeBuilder.category(Biome.Category.NONE);
		biomeBuilder.precipitation(Biome.Precipitation.RAIN);
		theBiome = biomeBuilder.build();
		Registry.register(BuiltinRegistries.BIOME, NoobMod.NoobBio_KEY.getValue(), theBiome);
	}
}
