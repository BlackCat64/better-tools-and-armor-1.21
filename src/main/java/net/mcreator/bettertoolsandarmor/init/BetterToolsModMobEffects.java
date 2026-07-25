/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.procedures.PitfallEffectExpiresProcedure;
import net.mcreator.bettertoolsandarmor.procedures.FrozenEffectExpiresProcedure;
import net.mcreator.bettertoolsandarmor.potion.*;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

@EventBusSubscriber
public class BetterToolsModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, BetterToolsMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> KARMA_POTION = REGISTRY.register("karma_potion", KarmaPotionMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> DOUBLE_JUMP = REGISTRY.register("double_jump", DoubleJumpMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> SWIFT_SWIM = REGISTRY.register("swift_swim", SwiftSwimMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> FROZEN = REGISTRY.register("frozen", FrozenMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> CRITICALITY = REGISTRY.register("criticality", CriticalityMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> PITFALL = REGISTRY.register("pitfall", PitfallMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> ORE_VISION = REGISTRY.register("ore_vision", OreVisionMobEffect::new);

	@SubscribeEvent
	public static void onEffectRemoved(MobEffectEvent.Remove event) {
		MobEffectInstance effectInstance = event.getEffectInstance();
		if (effectInstance != null) {
			expireEffects(event.getEntity(), effectInstance);
		}
	}

	@SubscribeEvent
	public static void onEffectExpired(MobEffectEvent.Expired event) {
		MobEffectInstance effectInstance = event.getEffectInstance();
		if (effectInstance != null) {
			expireEffects(event.getEntity(), effectInstance);
		}
	}

	private static void expireEffects(Entity entity, MobEffectInstance effectInstance) {
		if (effectInstance.getEffect().is(FROZEN)) {
			FrozenEffectExpiresProcedure.execute(entity.level(), entity);
		} else if (effectInstance.getEffect().is(PITFALL)) {
			PitfallEffectExpiresProcedure.execute(entity.level(), entity);
		}
	}
}