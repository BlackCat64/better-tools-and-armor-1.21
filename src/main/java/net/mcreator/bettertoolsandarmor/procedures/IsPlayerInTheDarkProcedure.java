package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class IsPlayerInTheDarkProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return false;
		double time = 0;
		time = world.dayTime() % 24000;
		if (time >= 13000 || !((entity.level().dimension()) == Level.OVERWORLD)) {
			return world.getBrightness(LightLayer.BLOCK, BlockPos.containing(x, y, z)) <= 4;
		}
		return world.getBrightness(LightLayer.SKY, BlockPos.containing(x, y, z)) <= 4 && world.getBrightness(LightLayer.BLOCK, BlockPos.containing(x, y, z)) <= 4;
	}
}