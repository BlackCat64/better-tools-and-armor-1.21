package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

@EventBusSubscriber
public class TestMessageDisplayProcedure {
	@SubscribeEvent
	public static void onBlockBreaking(PlayerEvent.BreakSpeed event) {
		if (event.getPosition().isEmpty())
			return;
		execute(event, event.getState(), event.getEntity());
	}

	public static void execute(BlockState blockstate, Entity entity) {
		execute(null, blockstate, entity);
	}

	private static void execute(@Nullable Event event, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		if (false) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("" + ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).isCorrectToolForDrops(blockstate)))), true);
		}
	}
}