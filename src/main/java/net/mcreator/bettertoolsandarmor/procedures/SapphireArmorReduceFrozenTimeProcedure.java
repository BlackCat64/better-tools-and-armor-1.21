package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModMobEffects;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@EventBusSubscriber
public class SapphireArmorReduceFrozenTimeProcedure {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Pre event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if ((IsWearingArmorTagProcedure.execute(entity, "minecraft:freeze_immune_wearables") || HasCuriosItemEquippedProcedure.execute(world, entity, new ItemStack(BetterToolsModItems.ICY_BRACELET.get())))
				&& (entity instanceof LivingEntity _livEnt && _livEnt.hasEffect(BetterToolsModMobEffects.FROZEN) ? _livEnt.getEffect(BetterToolsModMobEffects.FROZEN).getDuration() : 0) > 100) {
			if (entity instanceof LivingEntity _entity)
				_entity.removeEffect(BetterToolsModMobEffects.FROZEN);
			DeleteEntityIceBlockDisplayProcedure.execute(world, entity, false);
			if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
				_entity.addEffect(new MobEffectInstance(BetterToolsModMobEffects.FROZEN, 100, 0, false, true));
		}
		if (HasCuriosItemEquippedProcedure.execute(world, entity, new ItemStack(BetterToolsModItems.ICY_BRACELET.get())) && !(entity instanceof LivingEntity _livEnt3 && _livEnt3.hasEffect(BetterToolsModMobEffects.FROZEN))) {
			entity.setTicksFrozen(0);
		}
	}
}