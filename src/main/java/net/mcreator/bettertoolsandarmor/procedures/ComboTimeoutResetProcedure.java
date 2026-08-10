package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;

import javax.annotation.Nullable;

@EventBusSubscriber
public class ComboTimeoutResetProcedure {
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
		if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_last_attacked > 40
				|| !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:combo_weapons"))))) {
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ResourceLocation.parse("better_tools:crystallite_sword_honey_1"));
			}
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ResourceLocation.parse("better_tools:crystallite_sword_honey_2"));
			}
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ResourceLocation.parse("better_tools:crystallite_sword_honey_3"));
			}
		}
		if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_last_mined > 100
				|| entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_last_mined > 40 && entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).block_mining_combo >= 9
				|| !((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:combo_mining_tools")))
						|| (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:combo_harvesting_tools"))))) {
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.block_mining_combo = 0;
				_vars.markSyncDirty();
			}
		}
		if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_last_jumped > 30 || !IsWearingArmorTagProcedure.execute(entity, "better_tools:combo_jump_armor")) {
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(Attributes.JUMP_STRENGTH).removeModifier(ResourceLocation.parse("better_tools:combo_jump_1"));
			}
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(Attributes.SAFE_FALL_DISTANCE).removeModifier(ResourceLocation.parse("better_tools:combo_jump_1"));
			}
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(Attributes.JUMP_STRENGTH).removeModifier(ResourceLocation.parse("better_tools:combo_jump_2"));
			}
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(Attributes.SAFE_FALL_DISTANCE).removeModifier(ResourceLocation.parse("better_tools:combo_jump_2"));
			}
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(Attributes.JUMP_STRENGTH).removeModifier(ResourceLocation.parse("better_tools:combo_jump_3"));
			}
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(Attributes.SAFE_FALL_DISTANCE).removeModifier(ResourceLocation.parse("better_tools:combo_jump_3"));
			}
		}
	}
}