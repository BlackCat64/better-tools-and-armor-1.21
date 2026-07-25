package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class NearbyLavaCheckProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		double x_iterator = 0;
		double y_iterator = 0;
		double z_iterator = 0;
		boolean found = false;
		x_iterator = x - 2;
		y_iterator = y - 2;
		z_iterator = z - 2;
		for (int index52 = 0; index52 < 5; index52++) {
			for (int index53 = 0; index53 < 5; index53++) {
				for (int index54 = 0; index54 < 5; index54++) {
					if ((world.getBlockState(BlockPos.containing(x_iterator, y_iterator, z_iterator))).getBlock() == Blocks.LAVA || (world.getBlockState(BlockPos.containing(x_iterator, y_iterator, z_iterator))).getBlock() == Blocks.AIR) {
						found = true;
					}
					x_iterator = x_iterator + 1;
					if (found) {
						break;
					}
				}
				y_iterator = y_iterator + 1;
				if (found) {
					break;
				}
			}
			z_iterator = z_iterator + 1;
			if (found) {
				break;
			}
		}
		return found;
	}
}