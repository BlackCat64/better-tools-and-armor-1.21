package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class GetBlockXPDropProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null || !(entity instanceof LivingEntity))
			return 0;
		BlockPos pos = BlockPos.containing(x, y, z);
		BlockEntity be = world.getBlockEntity(pos);
		return blockstate.getExpDrop(world, pos, be, entity, ((LivingEntity) entity).getMainHandItem());
	}
}