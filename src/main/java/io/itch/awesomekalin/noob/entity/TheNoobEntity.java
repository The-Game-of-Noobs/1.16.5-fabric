
package io.itch.awesomekalin.noob.entity;

import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.World;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.StepAndDestroyBlockGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;

import io.itch.awesomekalin.noob.item.NoobTabItemGroup;
import io.itch.awesomekalin.noob.NoobMod;

@SuppressWarnings("deprecation")
public class TheNoobEntity extends PassiveEntity {
	public static final EntityType<TheNoobEntity> ENTITY = Registry.register(Registry.ENTITY_TYPE, NoobMod.id("the_noob"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TheNoobEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f))
					.trackRangeBlocks(64).forceTrackedVelocityUpdates(true).trackedUpdateRate(3).build());
	protected TheNoobEntity(EntityType<? extends TheNoobEntity> entityType, World world) {
		super(entityType, world);
		this.setAiDisabled(false);
		this.experiencePoints = 100;
	}

	public static void init() {
		FabricDefaultAttributeRegistry.register(ENTITY,
				TheNoobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 2).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.05)
						.add(EntityAttributes.GENERIC_ARMOR, 0).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0)
						.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100));
		Registry.register(Registry.ITEM, NoobMod.id("the_noob_spawn_egg"),
				new SpawnEggItem(ENTITY, -13421569, -10027009, new FabricItemSettings().group(NoobTabItemGroup.get())));
		BiomeModifications.create(new Identifier("noob", "thenoob_entity_spawn")).add(ModificationPhase.ADDITIONS, BiomeSelectors.all(),
				ctx -> ctx.getSpawnSettings().addSpawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ENTITY, 20, 1, 1)));
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		this.goalSelector.add(1, new WanderAroundGoal(this, 1));
		this.goalSelector.add(2, new LookAroundGoal(this));
		this.goalSelector.add(3, new SwimGoal(this));
		this.goalSelector.add(4, new StepAndDestroyBlockGoal(Blocks.GRASS_BLOCK, this, 1, (int) 3));
		this.goalSelector.add(5, new StepAndDestroyBlockGoal(Blocks.DIRT, this, 1, (int) 3));
		this.goalSelector.add(6, new EatGrassGoal(this));
	}

	@Override
	protected void dropLoot(DamageSource source, boolean causedByPlayer) {
		super.dropLoot(source, causedByPlayer);
		this.dropStack(new ItemStack(NoobMod.Saliva_ITEM));
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return new SoundEvent(new Identifier("entity.pig.ambient"));
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return new SoundEvent(new Identifier("entity.pig.hurt"));
	}

	@Override
	protected SoundEvent getDeathSound() {
		return new SoundEvent(new Identifier("entity.pig.death"));
	}

	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(new SoundEvent(new Identifier("entity.pig.step")), 0.15f, 1);
	}
}
