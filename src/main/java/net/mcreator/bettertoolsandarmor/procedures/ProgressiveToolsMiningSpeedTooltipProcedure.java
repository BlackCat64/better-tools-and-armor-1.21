package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class ProgressiveToolsMiningSpeedTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, Minecraft.getInstance().level, event.getItemStack(), event.getToolTip());
	}

	public static void execute(LevelAccessor world, ItemStack itemstack, List<Component> tooltip) {
		execute(null, world, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		double amount = 0;
		double replaceLine = 0;
		double efficiency = 0;
		String tooltipText = "";
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:progressive_tools"))) && itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_1") > 0) {
			if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined") >= itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_2")) {
				if (itemstack.is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))) {
					amount = 24;
				} else {
					amount = 12;
				}
			} else if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined") >= itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_1")) {
				if (itemstack.is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))) {
					amount = 12;
				} else {
					amount = 6;
				}
			}
			if (amount > 0) {
				if (itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.EFFICIENCY)) != 0) {
					efficiency = Math.pow(itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.EFFICIENCY)), 2) + 1;
					amount = amount + efficiency;
				}
				tooltipText = "\u00A79+" + new java.text.DecimalFormat("##.##").format(amount) + " Mining Efficiency";
				replaceLine = GetTooltipLineContainingProcedure.execute("Mining Efficiency", tooltip);
				if (replaceLine >= 0) {
					ReplaceTooltipLineProcedure.execute(replaceLine, tooltipText, tooltip);
				} else {
					replaceLine = GetTooltipLineContainingProcedure.execute("Attack Speed", tooltip) + 1;
					tooltip.add((int) replaceLine, Component.literal(tooltipText));
				}
			}
		}
	}
}