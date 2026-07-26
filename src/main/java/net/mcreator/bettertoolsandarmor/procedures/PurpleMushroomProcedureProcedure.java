package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.entity.PurpleMooshroomEntity;

public class PurpleMushroomProcedureProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		Vec3 movement = Vec3.ZERO;
		if (!(entity instanceof PurpleMooshroomEntity)) {
			movement = entity.getDeltaMovement();
			entity.hurt(new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("better_tools:poison_mushroom_damage")))), 1);
			entity.setDeltaMovement(new Vec3((movement.x()), (movement.y()), (movement.z())));
		}
	}
}