package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;

import javax.annotation.Nullable;

@EventBusSubscriber
public class EndTitaniumLeggingsProcedureProcedure {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Pre event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (IsWearingArmorTagProcedure.execute(entity, "better_tools:levitation_immune_armor") && entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(MobEffects.LEVITATION)) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(MobEffects.LEVITATION);
		}
	}
}