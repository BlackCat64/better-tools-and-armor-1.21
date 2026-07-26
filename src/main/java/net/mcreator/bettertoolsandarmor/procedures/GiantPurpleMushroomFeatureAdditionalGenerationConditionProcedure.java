package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;

public class GiantPurpleMushroomFeatureAdditionalGenerationConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		if (world instanceof ServerLevel _level) {
			_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("" + new Vec3(x, y, z))), false);
		}
		return true;
	}
}