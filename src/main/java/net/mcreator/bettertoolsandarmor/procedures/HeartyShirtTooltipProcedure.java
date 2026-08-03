package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class HeartyShirtTooltipProcedure {
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
		if (itemstack.getItem() == BetterToolsModItems.HEARTY_CHESTPLATE.get()) {
			tooltip.add(Component.literal("\u00A77Energy Cost: \u00A7c150 \u00A76/ 1HP / 5s"));
		} else if (itemstack.getItem() == BetterToolsModItems.IRON_HEARTY_CHESTPLATE.get()) {
			tooltip.add(Component.literal("\u00A77Energy Cost: \u00A7c120 \u00A76/ 1HP / 5s"));
		} else if (itemstack.getItem() == BetterToolsModItems.DIAMOND_HEARTY_CHESTPLATE.get()) {
			tooltip.add(Component.literal("\u00A77Energy Cost: \u00A7c90 \u00A76/ 1HP / 5s"));
		}
	}
}