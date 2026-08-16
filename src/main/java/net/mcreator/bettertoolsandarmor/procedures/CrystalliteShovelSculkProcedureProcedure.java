package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteShovelSculkProcedureProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getState(), event.getPlayer());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		execute(null, world, x, y, z, blockstate, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		double x_disp = 0;
		double y_disp = 0;
		double z_disp = 0;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:excavating_shovels"))) && !entity.isShiftKeyDown()
				&& blockstate.is(BlockTags.create(ResourceLocation.parse("minecraft:mineable/shovel")))) {
			if (entity.getXRot() > 40 || entity.getXRot() < -40) {
				x_disp = -1;
				for (int index918 = 0; index918 < 3; index918++) {
					z_disp = -1;
					for (int index919 = 0; index919 < 3; index919++) {
						if (!(x_disp == 0 && y_disp == 0 && z_disp == 0)) {
							if ((world.getBlockState(BlockPos.containing(x + x_disp, y, z + z_disp))).is(BlockTags.create(ResourceLocation.parse("minecraft:mineable/shovel")))) {
								BreakBlockWithShovelProcedure.execute(world, x + x_disp, y, z + z_disp, entity, entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
							}
						}
						z_disp = z_disp + 1;
					}
					x_disp = x_disp + 1;
				}
			} else if ((entity.getDirection()) == Direction.NORTH || (entity.getDirection()) == Direction.SOUTH) {
				x_disp = -1;
				for (int index920 = 0; index920 < 3; index920++) {
					y_disp = -1;
					for (int index921 = 0; index921 < 3; index921++) {
						if (!(x_disp == 0 && y_disp == 0 && z_disp == 0)) {
							if ((world.getBlockState(BlockPos.containing(x + x_disp, y + y_disp, z))).is(BlockTags.create(ResourceLocation.parse("minecraft:mineable/shovel")))) {
								BreakBlockWithShovelProcedure.execute(world, x + x_disp, y + y_disp, z, entity, entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
							}
						}
						y_disp = y_disp + 1;
					}
					x_disp = x_disp + 1;
				}
			} else if ((entity.getDirection()) == Direction.WEST || (entity.getDirection()) == Direction.EAST) {
				z_disp = -1;
				for (int index922 = 0; index922 < 3; index922++) {
					y_disp = -1;
					for (int index923 = 0; index923 < 3; index923++) {
						if (!(x_disp == 0 && y_disp == 0 && z_disp == 0)) {
							if ((world.getBlockState(BlockPos.containing(x, y + y_disp, z + z_disp))).is(BlockTags.create(ResourceLocation.parse("minecraft:mineable/shovel")))) {
								BreakBlockWithShovelProcedure.execute(world, x, y + y_disp, z + z_disp, entity, entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
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