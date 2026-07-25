package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class PurpleMushroomSpaceCheckProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		sx = -2;
		found = false;
		for (int index6 = 0; index6 < 5; index6++) {
			sy = 0;
			for (int index7 = 0; index7 < 9; index7++) {
				sz = -2;
				for (int index8 = 0; index8 < 5; index8++) {
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