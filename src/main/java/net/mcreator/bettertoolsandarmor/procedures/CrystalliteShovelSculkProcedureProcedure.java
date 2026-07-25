package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class CrystalliteShovelSculkProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double x_disp = 0;
		double y_disp = 0;
		double z_disp = 0;
		x_disp = 0;
		y_disp = 0;
		z_disp = 0;
		if (!entity.isShiftKeyDown() && blockstate.is(BlockTags.create(ResourceLocation.parse("minecraft:mineable/shovel")))) {
			if (entity.getXRot() > 40 || entity.getXRot() < -40) {
				x_disp = -1;
				for (int index17 = 0; index17 < 3; index17++) {
					z_disp = -1;
					for (int index18 = 0; index18 < 3; index18++) {
						if (!(x_disp == 0 && y_disp == 0 && z_disp == 0)) {
							if ((world.getBlockState(BlockPos.containing(x + x_disp, y, z + z_disp))).is(BlockTags.create(ResourceLocation.parse("minecraft:mineable/shovel")))) {
								BreakBlockWithShovelProcedure.execute(world, x + x_disp, y, z + z_disp, entity, itemstack);
							}
						}
						z_disp = z_disp + 1;
					}
					x_disp = x_disp + 1;
				}
			} else if ((entity.getDirection()) == Direction.NORTH || (entity.getDirection()) == Direction.SOUTH) {
				x_disp = -1;
				for (int index19 = 0; index19 < 3; index19++) {
					y_disp = -1;
					for (int index20 = 0; index20 < 3; index20++) {
						if (!(x_disp == 0 && y_disp == 0 && z_disp == 0)) {
							if ((world.getBlockState(BlockPos.containing(x + x_disp, y + y_disp, z))).is(BlockTags.create(ResourceLocation.parse("minecraft:mineable/shovel")))) {
								BreakBlockWithShovelProcedure.execute(world, x + x_disp, y + y_disp, z, entity, itemstack);
							}
						}
						y_disp = y_disp + 1;
					}
					x_disp = x_disp + 1;
				}
			} else if ((entity.getDirection()) == Direction.WEST || (entity.getDirection()) == Direction.EAST) {
				z_disp = -1;
				for (int index21 = 0; index21 < 3; index21++) {
					y_disp = -1;
					for (int index22 = 0; index22 < 3; index22++) {
						if (!(x_disp == 0 && y_disp == 0 && z_disp == 0)) {
							if ((world.getBlockState(BlockPos.containing(x, y + y_disp, z + z_disp))).is(BlockTags.create(ResourceLocation.parse("minecraft:mineable/shovel")))) {
								BreakBlockWithShovelProcedure.execute(world, x, y + y_disp, z + z_disp, entity, itemstack);
							}
						}
						y_disp = y_disp + 1;
					}
					z_disp = z_disp + 1;
				}
			}
		}
	}
}