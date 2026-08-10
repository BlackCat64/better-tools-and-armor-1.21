package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteLeggingsPrismarineLowGravityProcedure {
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
		if (IsWearingArmorTagProcedure.execute(entity, "better_tools:low_gravity_in_water_armor") && entity.isInWaterRainOrBubble()) {
			if (entity.isInWaterOrBubble()) {
				entity.setNoGravity(true);
			} else {
				entity.setNoGravity(false);
				if (entity instanceof LivingEntity _entity) {
					AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:crystallite_leggings_prismarine"), (-0.5), AttributeModifier.Operation.ADD_MULTIPLIED_BASE);
					if (!_entity.getAttribute(Attributes.GRAVITY).hasModifier(modifier.id())) {
						_entity.getAttribute(Attributes.GRAVITY).addPermanentModifier(modifier);
					}
				}
			}
		} else {
			entity.setNoGravity(false);
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(Attributes.GRAVITY).removeModifier(ResourceLocation.parse("better_tools:crystallite_leggings_prismarine"));
			}
		}
	}
}