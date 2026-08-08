package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.component.DataComponents;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class ProgressiveToolsTooltipProcedure {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		execute(event, event.getItemStack(), event.getToolTip());
	}

	public static void execute(ItemStack itemstack, List<Component> tooltip) {
		execute(null, itemstack, tooltip);
	}

	private static void execute(@Nullable Event event, ItemStack itemstack, List<Component> tooltip) {
		if (tooltip == null)
			return;
		double tooltipLine = 0;
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:progressive_tools")))) {
			tooltipLine = GetTooltipLineContainingProcedure.execute("Calibrated", tooltip) + 1;
			if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined") < itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_1")
					|| itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_1") <= 0) {
				tooltip.add((int) tooltipLine, Component.literal("\u00A7c[Lv. 0 / 2]"));
			} else if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined") >= itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_2")) {
				tooltip.add((int) tooltipLine, Component.literal("\u00A7c[Max]"));
			} else if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined") >= itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_1")) {
				tooltip.add((int) tooltipLine, Component.literal("\u00A7c[Lv. 1 / 2]"));
			}
			if (itemstack.is(ItemTags.create(ResourceLocation.parse("minecraft:shovels"))) || itemstack.is(ItemTags.create(ResourceLocation.parse("minecraft:hoes")))) {
				tooltip.add(Component.literal("\u00A77When destroying plants:"));
				if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined") >= itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_2")) {
					tooltip.add(Component.literal("\u00A7220% Bone Meal Chance"));
				} else if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined") >= itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_1")) {
					tooltip.add(Component.literal("\u00A7210% Bone Meal Chance"));
				} else {
					tooltip.add(Component.literal("\u00A725% Bone Meal Chance"));
				}
			}
			tooltip.add(Component.literal(((itemstack.is(ItemTags.create(ResourceLocation.parse("minecraft:hoes"))) ? "\u00A77Uses: \u00A76" : "\u00A77Blocks Mined: \u00A76") + ""
					+ new java.text.DecimalFormat("##.##").format(itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined")))));
			if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined") < itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_1")) {
				tooltip.add(Component.literal(("\u00A77Next Upgrade at: \u00A76" + new java.text.DecimalFormat("#").format(itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_1")))));
			} else if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("blocks_mined") < itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_2")) {
				tooltip.add(Component.literal(("\u00A77Next Upgrade at: \u00A76" + new java.text.DecimalFormat("#").format(itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_2")))));
			}
		}
	}
}