package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class VeinMiningProcedureProcedure {
	public static double execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity, double recursion_depth) {
		if (entity == null)
			return 0;
		double count = 0;
		double y_offset = 0;
		double x_offset = 0;
		double z_offset = 0;
		BlockState block = Blocks.AIR.defaultBlockState();
		if (recursion_depth < 6) {
			x_offset = 0;
			for (int index84 = 0; index84 < 3; index84++) {
				y_offset = 0;
				for (int index85 = 0; index85 < 3; index85++) {
					z_offset = 0;
					for (int index86 = 0; index86 < 3; index86++) {
						if (!(x_offset == 0 && y_offset == 0 && z_offset == 0) && (world.getBlockState(BlockPos.containing(x + x_offset, y + y_offset, z + z_offset))).getBlock() == blockstate.getBlock()) {
							BreakBlockWithPickaxeProcedure.execute(world, x + x_offset, y + y_offset, z + z_offset, blockstate, entity);
							count = count + 1;
							count = count + VeinMiningProcedureProcedure.execute(world, x + x_offset, y + y_offset, z + z_offset, blockstate, entity, recursion_depth + 1);
						}
						z_offset = z_offset + 1;
						if (z_offset > 1) {
							z_offset = -1;
						}
					}
					y_offset = y_offset + 1;
					if (y_offset > 1) {
						y_offset = -1;
					}
				}
				x_offset = x_offset + 1;
				if (x_offset > 1) {
					x_offset = -1;
				}
			}
		}
		return count;
	}
}