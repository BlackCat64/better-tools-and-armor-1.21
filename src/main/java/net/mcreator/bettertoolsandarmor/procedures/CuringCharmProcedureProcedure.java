package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber
public class CuringCharmProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		int shorten_time = 100; // The charm shortens all negative effects to 5s (100 ticks)
		if (HasCuriosItemEquippedProcedure.execute(world, entity, new ItemStack(BetterToolsModItems.CURING_CHARM.get())) && entity instanceof LivingEntity living) {
			List<MobEffectInstance> harmfulEffects = living.getActiveEffects().stream().filter(instance -> instance.getEffect().value().getCategory() == MobEffectCategory.HARMFUL).toList();
			for (MobEffectInstance effect : harmfulEffects) {
				if (effect.getDuration() > shorten_time) {
					living.removeEffect(effect.getEffect());
					living.addEffect(new MobEffectInstance(effect.getEffect(), shorten_time, effect.getAmplifier(), effect.isAmbient(), effect.isVisible(), effect.showIcon()));
				}
			}
		}
	}
}