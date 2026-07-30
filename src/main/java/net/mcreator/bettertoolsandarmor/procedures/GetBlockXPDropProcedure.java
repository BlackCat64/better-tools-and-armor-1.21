package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class GetBlockXPDropProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null || !(entity instanceof LivingEntity))
			return 0;
		if (false) {
			// Gets MCreator to recognise dependencies
			BetterToolsMod.LOGGER.info(blockstate);
			if (world instanceof ServerLevel _level) {
				_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Message"), false);
			}
			BetterToolsMod.LOGGER.info(x);
			BetterToolsMod.LOGGER.info(y);
			BetterToolsMod.LOGGER.info(z);
			BetterToolsMod.LOGGER.info(entity);
		}
		BlockPos pos = BlockPos.containing(x, y, z);
		BlockEntity be = world.getBlockEntity(pos);
		return blockstate.getExpDrop(world, pos, be, entity, ((LivingEntity) entity).getMainHandItem());
	}
}