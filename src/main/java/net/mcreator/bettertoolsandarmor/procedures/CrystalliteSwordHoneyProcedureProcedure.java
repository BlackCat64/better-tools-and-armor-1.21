package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteSwordHoneyProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingIncomingDamageEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getSource().getEntity());
		}
	}

	public static void execute(Entity sourceentity) {
		execute(null, sourceentity);
	}

	private static void execute(@Nullable Event event, Entity sourceentity) {
		if (sourceentity == null)
			return;
		if (sourceentity instanceof Player) {
			{
				BetterToolsModVariables.PlayerVariables _vars = sourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.time_since_last_attacked = 0;
				_vars.markSyncDirty();
			}
			if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:combo_weapons")))) {
				if (sourceentity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_last_attacked <= 40) {
					if (sourceentity instanceof LivingEntity _livingEntity3 && _livingEntity3.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(ResourceLocation.parse("better_tools:crystallite_sword_honey_1"))) {
						if (sourceentity instanceof LivingEntity _entity) {
							_entity.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ResourceLocation.parse("better_tools:crystallite_sword_honey_1"));
						}
						if (sourceentity instanceof LivingEntity _entity) {
							AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:crystallite_sword_honey_2"), 2, AttributeModifier.Operation.ADD_VALUE);
							if (!_entity.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(modifier.id())) {
								_entity.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(modifier);
							}
						}
					} else if (sourceentity instanceof LivingEntity _livingEntity6 && _livingEntity6.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(ResourceLocation.parse("better_tools:crystallite_sword_honey_2"))) {
						if (sourceentity instanceof LivingEntity _entity) {
							_entity.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ResourceLocation.parse("better_tools:crystallite_sword_honey_1"));
						}
						if (sourceentity instanceof LivingEntity _entity) {
							_entity.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ResourceLocation.parse("better_tools:crystallite_sword_honey_2"));
						}
						if (sourceentity instanceof LivingEntity _entity) {
							AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:crystallite_sword_honey_3"), 3, AttributeModifier.Operation.ADD_VALUE);
							if (!_entity.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(modifier.id())) {
								_entity.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(modifier);
							}
						}
					}
				}
				if (!(sourceentity instanceof LivingEntity _livingEntity10 && _livingEntity10.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(ResourceLocation.parse("better_tools:crystallite_sword_honey_1"))
						|| sourceentity instanceof LivingEntity _livingEntity11 && _livingEntity11.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(ResourceLocation.parse("better_tools:crystallite_sword_honey_2"))
						|| sourceentity instanceof LivingEntity _livingEntity12 && _livingEntity12.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(ResourceLocation.parse("better_tools:crystallite_sword_honey_3")))) {
					if (sourceentity instanceof LivingEntity _entity) {
						AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:crystallite_sword_honey_1"), 1, AttributeModifier.Operation.ADD_VALUE);
						if (!_entity.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(modifier.id())) {
							_entity.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(modifier);
						}
					}
				}
			}
		}
	}
}