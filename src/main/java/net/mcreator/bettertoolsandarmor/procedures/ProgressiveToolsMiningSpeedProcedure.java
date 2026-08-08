package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.component.DataComponents;

import javax.annotation.Nullable;

@EventBusSubscriber
public class ProgressiveToolsMiningSpeedProcedure {
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
		double amount = 0;
		if (entity instanceof LivingEntity _entity) {
			_entity.getAttribute(Attributes.MINING_EFFICIENCY).removeModifier(ResourceLocation.parse("better_tools:progressive_tools"));
		}
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:progressive_tools")))) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag()
					.getDouble("blocks_mined") >= (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_2")) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))) {
					amount = 24;
				} else {
					amount = 12;
				}
			} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag()
					.getDouble("blocks_mined") >= (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_1")) {
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))) {
					amount = 12;
				} else {
					amount = 6;
				}
			}
			if (amount > 0) {
				if (entity instanceof LivingEntity _entity) {
					AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:progressive_tools"), amount, AttributeModifier.Operation.ADD_VALUE);
					if (!_entity.getAttribute(Attributes.MINING_EFFICIENCY).hasModifier(modifier.id())) {
						_entity.getAttribute(Attributes.MINING_EFFICIENCY).addPermanentModifier(modifier);
					}
				}
			}
		}
	}
}