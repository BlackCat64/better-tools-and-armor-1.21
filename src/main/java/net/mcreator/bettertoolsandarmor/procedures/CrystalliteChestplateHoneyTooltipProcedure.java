package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class CrystalliteChestplateHoneyTooltipProcedure {
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
		if (itemstack.getItem() == BetterToolsModItems.CRYSTALLITE_ARMOR_HONEY_CHESTPLATE.get()) {
			if (entity instanceof LivingEntity _livingEntity2 && _livingEntity2.getAttribute(Attributes.MAX_HEALTH).hasModifier(ResourceLocation.parse("better_tools:crystallite_chestplate_honey"))) {
				tooltip.add(Component.literal(("\u00A79+" + (new java.text.DecimalFormat("##.##").format((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 3)) + " Max Absorption")));
			} else {
				tooltip.add(Component.literal(("\u00A79+" + (new java.text.DecimalFormat("##.##").format(Math.floor((entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1) * 1.5))) + " Max Absorption")));
			}
			tooltip.add(Component.literal("\u00A77Absorption Gain:"));
			tooltip.add(Component.literal("\u00A79+2HP every 10s"));
		}
	}
}