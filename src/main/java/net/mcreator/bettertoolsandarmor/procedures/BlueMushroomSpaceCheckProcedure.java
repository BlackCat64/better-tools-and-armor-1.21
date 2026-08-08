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
		for (int index12 = 0; index12 < 10; index12++) {
			sy = 0;
			for (int index13 = 0; index13 < 9; index13++) {
				sz = -5;
				for (int index14 = 0; index14 < 10; index14++) {
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