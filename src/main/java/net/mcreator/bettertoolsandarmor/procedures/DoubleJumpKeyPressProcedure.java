package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.network.BetterToolsModVariables;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

public class DoubleJumpKeyPressProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		boolean boots = false;
		ItemStack vial = ItemStack.EMPTY;
		double jump_strength = 0;
		if (entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).extra_jumps > 0 && !(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false) && !entity.onGround() && !entity.isInWater()
				&& !(entity instanceof LivingEntity _livEnt3 && _livEnt3.isFallFlying())) {
			jump_strength = entity instanceof LivingEntity _livingEntity4 && _livingEntity4.getAttributes().hasAttribute(Attributes.JUMP_STRENGTH) ? _livingEntity4.getAttribute(Attributes.JUMP_STRENGTH).getValue() : 0;
			if (entity instanceof LivingEntity _livEnt5 && _livEnt5.hasEffect(MobEffects.JUMP)) {
				jump_strength = jump_strength + 0.1 * ((entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(MobEffects.JUMP) ? _livEnt.getEffect(MobEffects.JUMP).getAmplifier() : 0) + 1);
			}
			entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x()), jump_strength, (entity.getDeltaMovement().z())));
			if (!(entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).time_since_on_ground <= 2 && entity.getY() < entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).last_on_ground_y)) {
				{
					BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
					_vars.extra_jumps = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES).extra_jumps - 1;
					_vars.markSyncDirty();
				}
			}
			{
				BetterToolsModVariables.PlayerVariables _vars = entity.getData(BetterToolsModVariables.PLAYER_VARIABLES);
				_vars.time_since_last_jumped = 0;
				_vars.markSyncDirty();
			}
			entity.fallDistance = 0;
			if (entity instanceof ServerPlayer _player) {
				AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:double_jump_adv"));
				if (_adv != null) {
					AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
					if (!_ap.isDone()) {
						for (String criteria : _ap.getRemainingCriteria())
							_player.getAdvancements().award(_adv, criteria);
					}
				}
			}
			if (entity instanceof LivingEntity _livingEntity13 && _livingEntity13.getAttribute(BetterToolsModAttributes.EXTRA_JUMPS).hasModifier(ResourceLocation.parse("better_tools:winged_boots"))
					&& !(entity instanceof LivingEntity _livEnt14 && _livEnt14.hasEffect(BetterToolsModMobEffects.DOUBLE_JUMP))) {
				vial = GetEquippedVialProcedure.execute(world, entity);
				{
					final String _tagName = "energy";
					final double _tagValue = (vial.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("energy") - 50);
					CustomData.update(DataComponents.CUSTOM_DATA, vial, tag -> tag.putDouble(_tagName, _tagValue));
				}
			}
		}
	}
}