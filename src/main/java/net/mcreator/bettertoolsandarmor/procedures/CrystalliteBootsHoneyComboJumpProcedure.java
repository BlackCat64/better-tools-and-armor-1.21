package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteBootsHoneyComboJumpProcedure {
	@SubscribeEvent
	public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player) {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.time_since_last_jumped = 0;
				_vars.markSyncDirty();
			}
			if (IsWearingArmorTagProcedure.execute(entity, "better_tools:combo_jump_armor")) {
				if (!(entity instanceof LivingEntity _livingEntity1 && _livingEntity1.getAttribute(Attributes.JUMP_STRENGTH).hasModifier(ResourceLocation.parse("better_tools:combo_jump_1"))
						|| entity instanceof LivingEntity _livingEntity2 && _livingEntity2.getAttribute(Attributes.JUMP_STRENGTH).hasModifier(ResourceLocation.parse("better_tools:combo_jump_2"))
						|| entity instanceof LivingEntity _livingEntity3 && _livingEntity3.getAttribute(Attributes.JUMP_STRENGTH).hasModifier(ResourceLocation.parse("better_tools:combo_jump_3")))) {
					if (entity instanceof LivingEntity _entity) {
						AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:combo_jump_1"), 0.2, AttributeModifier.Operation.ADD_VALUE);
						if (!_entity.getAttribute(Attributes.JUMP_STRENGTH).hasModifier(modifier.id())) {
							_entity.getAttribute(Attributes.JUMP_STRENGTH).addPermanentModifier(modifier);
						}
					}
					if (entity instanceof LivingEntity _entity) {
						AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:combo_jump_1"), 2, AttributeModifier.Operation.ADD_VALUE);
						if (!_entity.getAttribute(Attributes.SAFE_FALL_DISTANCE).hasModifier(modifier.id())) {
							_entity.getAttribute(Attributes.SAFE_FALL_DISTANCE).addPermanentModifier(modifier);
						}
					}
				} else if (entity instanceof LivingEntity _livingEntity6 && _livingEntity6.getAttribute(Attributes.JUMP_STRENGTH).hasModifier(ResourceLocation.parse("better_tools:combo_jump_1"))
						&& entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_last_jumped <= 20) {
					if (entity instanceof LivingEntity _entity) {
						_entity.getAttribute(Attributes.JUMP_STRENGTH).removeModifier(ResourceLocation.parse("better_tools:combo_jump_1"));
					}
					if (entity instanceof LivingEntity _entity) {
						AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:combo_jump_2"), 0.3, AttributeModifier.Operation.ADD_VALUE);
						if (!_entity.getAttribute(Attributes.JUMP_STRENGTH).hasModifier(modifier.id())) {
							_entity.getAttribute(Attributes.JUMP_STRENGTH).addPermanentModifier(modifier);
						}
					}
					if (entity instanceof LivingEntity _entity) {
						AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:combo_jump_2"), 3, AttributeModifier.Operation.ADD_VALUE);
						if (!_entity.getAttribute(Attributes.SAFE_FALL_DISTANCE).hasModifier(modifier.id())) {
							_entity.getAttribute(Attributes.SAFE_FALL_DISTANCE).addPermanentModifier(modifier);
						}
					}
				} else if (entity instanceof LivingEntity _livingEntity10 && _livingEntity10.getAttribute(Attributes.JUMP_STRENGTH).hasModifier(ResourceLocation.parse("better_tools:combo_jump_2"))
						&& entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_last_jumped <= 30) {
					if (entity instanceof LivingEntity _entity) {
						_entity.getAttribute(Attributes.JUMP_STRENGTH).removeModifier(ResourceLocation.parse("better_tools:combo_jump_1"));
					}
					if (entity instanceof LivingEntity _entity) {
						_entity.getAttribute(Attributes.JUMP_STRENGTH).removeModifier(ResourceLocation.parse("better_tools:combo_jump_2"));
					}
					if (entity instanceof LivingEntity _entity) {
						AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:combo_jump_3"), 0.4, AttributeModifier.Operation.ADD_VALUE);
						if (!_entity.getAttribute(Attributes.JUMP_STRENGTH).hasModifier(modifier.id())) {
							_entity.getAttribute(Attributes.JUMP_STRENGTH).addPermanentModifier(modifier);
						}
					}
					if (entity instanceof LivingEntity _entity) {
						AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:combo_jump_3"), 4, AttributeModifier.Operation.ADD_VALUE);
						if (!_entity.getAttribute(Attributes.SAFE_FALL_DISTANCE).hasModifier(modifier.id())) {
							_entity.getAttribute(Attributes.SAFE_FALL_DISTANCE).addPermanentModifier(modifier);
						}
					}
				} else if (entity instanceof LivingEntity _livingEntity15 && _livingEntity15.getAttribute(Attributes.JUMP_STRENGTH).hasModifier(ResourceLocation.parse("better_tools:combo_jump_3"))) {
					if (entity instanceof ServerPlayer _player) {
						AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:combo_jump_adv"));
						if (_adv != null) {
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
				}
			}
		}
	}
}