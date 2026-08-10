package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class CrystalliteBootsPrismarineTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getEntity(), event.getItemStack(), event.getToolTip());
	}

	public static void execute(Entity entity, ItemStack itemstack, List<Component> tooltip) {
		execute(null, entity, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, Entity entity, ItemStack itemstack, List<Component> tooltip) {
		if (entity == null || tooltip == null)
			return;
		boolean rain = false;
		boolean water = false;
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:swim_speed_boosting_armor")))) {
			if (entity.isInWater()) {
				water = true;
				rain = true;
				if (IsPlayerWearingItemProcedure.execute(entity, itemstack)) {
					tooltip.add(Component.literal(("\u00A72 "
							+ (new java.text.DecimalFormat("##.#")
									.format((entity instanceof LivingEntity _livingEntity3 && _livingEntity3.getAttributes().hasAttribute(NeoForgeMod.SWIM_SPEED) ? _livingEntity3.getAttribute(NeoForgeMod.SWIM_SPEED).getValue() : 0) * 100))
							+ "% Swim Speed")));
				} else {
					tooltip.add(Component.literal("\u00A79+50% Swim Speed"));
				}
			} else if (entity.isInWaterRainOrBubble()) {
				rain = true;
				if (IsPlayerWearingItemProcedure.execute(entity, itemstack)) {
					tooltip.add(Component.literal(("\u00A72 "
							+ (new java.text.DecimalFormat("##.#")
									.format((entity instanceof LivingEntity _livingEntity7 && _livingEntity7.getAttributes().hasAttribute(Attributes.MOVEMENT_SPEED) ? _livingEntity7.getAttribute(Attributes.MOVEMENT_SPEED).getValue() : 0) * 1000))
							+ "% Movement Speed")));
				} else {
					tooltip.add(Component.literal("\u00A79+30% Speed"));
				}
			}
			if (!water) {
				if (!rain) {
					tooltip.add(Component.literal("\u00A77When in rain:"));
					tooltip.add(Component.literal("\u00A79+30% Speed"));
				}
				tooltip.add(Component.literal("\u00A77When in water:"));
				tooltip.add(Component.literal("\u00A79+50% Swim Speed"));
			} else {
				if (!rain) {
					tooltip.add(Component.literal("\u00A77When in rain:"));
					tooltip.add(Component.literal("\u00A79+30% Speed"));
				}
			}
		}
	}
}