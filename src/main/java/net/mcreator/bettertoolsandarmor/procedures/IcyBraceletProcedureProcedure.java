package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

@EventBusSubscriber
public class IcyBraceletProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double time = 0;
		double chance = 0;
		if (entity instanceof LivingEntity _entity) {
			_entity.getAttribute(BetterToolsModAttributes.ATTACK_FREEZE_CHANCE).removeModifier(ResourceLocation.parse("better_tools:icy_bracelet"));
		}
		if (entity instanceof LivingEntity _entity) {
			_entity.getAttribute(BetterToolsModAttributes.ATTACK_FREEZE_TIME).removeModifier(ResourceLocation.parse("better_tools:icy_bracelet"));
		}
		if (HasCuriosItemEquippedProcedure.execute(world, entity, new ItemStack(BetterToolsModItems.ICY_BRACELET.get()))) {
			time = 100;
			chance = 0.1;
			if (IsInColdBiomeProcedure.execute(world, x, y, z)) {
				time = time * 2;
				chance = chance * 2;
			}
			if (!((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:freezing_weapons"))))) {
				chance = chance + (entity instanceof LivingEntity _livingEntity4 && _livingEntity4.getAttributes().hasAttribute(Attributes.LUCK) ? _livingEntity4.getAttribute(Attributes.LUCK).getValue() : 0) * 0.05;
				if (entity instanceof LivingEntity _entity) {
					AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:icy_bracelet"), time, AttributeModifier.Operation.ADD_VALUE);
					if (!_entity.getAttribute(BetterToolsModAttributes.ATTACK_FREEZE_TIME).hasModifier(modifier.id())) {
						_entity.getAttribute(BetterToolsModAttributes.ATTACK_FREEZE_TIME).addPermanentModifier(modifier);
					}
				}
			}
			if (entity instanceof LivingEntity _entity) {
				AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:icy_bracelet"), chance, AttributeModifier.Operation.ADD_VALUE);
				if (!_entity.getAttribute(BetterToolsModAttributes.ATTACK_FREEZE_CHANCE).hasModifier(modifier.id())) {
					_entity.getAttribute(BetterToolsModAttributes.ATTACK_FREEZE_CHANCE).addPermanentModifier(modifier);
				}
			}
		}
	}
}