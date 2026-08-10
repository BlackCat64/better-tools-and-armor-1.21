package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteChestplatePrismarineProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (IsWearingArmorTagProcedure.execute(entity, "better_tools:absorption_in_water_armor") && entity.isInWaterRainOrBubble() && entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).crystallite_prismarine_absorption_timer == 0) {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.crystallite_prismarine_absorption_timer = 200;
				_vars.markSyncDirty();
			}
			SetAbsorptionAmountProcedure.execute(entity, (entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0) + 2);
		}
	}
}