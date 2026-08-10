package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystallitePickaxeHoneyProcedureProcedure {
	@SubscribeEvent
	public static void onBlockBreaking(PlayerEvent.BreakSpeed event) {
		if (event.getPosition().isEmpty())
			return;
		execute(event, event.getEntity().level(), event.getPosition().get().getX(), event.getPosition().get().getY(), event.getPosition().get().getZ(), event.getState(), event.getEntity(), event.getNewSpeed());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity, double breakSpeed) {
		execute(null, world, x, y, z, blockstate, entity, breakSpeed);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity, double breakSpeed) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:combo_mining_tools")))
				&& world.getBlockState(BlockPos.containing(x, y, z)).getDestroySpeed(world, BlockPos.containing(x, y, z)) > 0 && entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).block_mining_combo >= 9) {
			if (blockstate.getBlock() == entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_mined_block.getBlock()) {
				if (event instanceof PlayerEvent.BreakSpeed _speed5)
					_speed5.setNewSpeed((float) (breakSpeed * 1000));
			}
		}
	}
}