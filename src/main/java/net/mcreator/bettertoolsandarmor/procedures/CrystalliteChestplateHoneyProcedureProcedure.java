package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteChestplateHoneyProcedureProcedure {
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
		if (IsWearingArmorTagProcedure.execute(entity, "better_tools:hive_heart_armor")) {
			if (!(entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttribute(Attributes.MAX_HEALTH).hasModifier(ResourceLocation.parse("better_tools:crystallite_chestplate_honey")))) {
				if (entity instanceof LivingEntity _entity) {
					AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:crystallite_chestplate_honey"), Math.floor((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 1.5),
							AttributeModifier.Operation.ADD_VALUE);
					if (!_entity.getAttribute(Attributes.MAX_ABSORPTION).hasModifier(modifier.id())) {
						_entity.getAttribute(Attributes.MAX_ABSORPTION).addPermanentModifier(modifier);
					}
				}
				SetAbsorptionAmountProcedure.execute(entity,
						(entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0) + Math.max((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) - (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) / 2, 0));
				if (entity instanceof LivingEntity _entity) {
					AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:crystallite_chestplate_honey"), (-0.5), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
					if (!_entity.getAttribute(Attributes.MAX_HEALTH).hasModifier(modifier.id())) {
						_entity.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(modifier);
					}
				}
			}
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) >= (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)) {
				if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).crystallite_honey_absorption_timer <= 0) {
					SetAbsorptionAmountProcedure.execute(entity, (entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0) + 2);
					{
						BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
						_vars.crystallite_honey_absorption_timer = 200;
						_vars.markSyncDirty();
					}
				}
			}
		} else if (entity instanceof LivingEntity _livingEntity10 && _livingEntity10.getAttribute(Attributes.MAX_HEALTH).hasModifier(ResourceLocation.parse("better_tools:crystallite_chestplate_honey"))) {
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(Attributes.MAX_HEALTH).removeModifier(ResourceLocation.parse("better_tools:crystallite_chestplate_honey"));
			}
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(Attributes.MAX_ABSORPTION).removeModifier(ResourceLocation.parse("better_tools:crystallite_chestplate_honey"));
			}
			if (entity instanceof LivingEntity _entity)
				_entity.setHealth(
						(float) ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + Math.min(entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0, entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)));
			if ((entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0) >= (entity instanceof LivingEntity _livingEntity18 && _livingEntity18.getAttributes().hasAttribute(Attributes.MAX_ABSORPTION)
					? _livingEntity18.getAttribute(Attributes.MAX_ABSORPTION).getValue()
					: 0)) {
				SetAbsorptionAmountProcedure.execute(entity,
						entity instanceof LivingEntity _livingEntity19 && _livingEntity19.getAttributes().hasAttribute(Attributes.MAX_ABSORPTION) ? _livingEntity19.getAttribute(Attributes.MAX_ABSORPTION).getValue() : 0);
			}
		}
	}
}