package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BlueMushroomSpaceCheckProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		sx = -5;
		found = false;
		for (int index27 = 0; index27 < 10; index27++) {
			sy = 0;
			for (int index28 = 0; index28 < 9; index28++) {
				sz = -5;
				for (int index29 = 0; index29 < 10; index29++) {
					if (world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz)).canOcclude()) {
						found = true;
					}
					sz = sz + 1;
				}
				sy = sy + 1;
			}
			sx = sx + 1;
		}
		return !found;
	}
}