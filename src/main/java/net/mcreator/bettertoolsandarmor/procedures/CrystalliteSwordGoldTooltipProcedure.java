package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class CrystalliteSwordGoldTooltipProcedure {
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
		double percent = 0;
		double damage = 0;
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:spin_attack_weapons")))) {
			percent = Math.min(0.6 + 0.1 * itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SWEEPING_EDGE)), 1);
			if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_swords")))) {
				damage = 9;
			} else if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:crystallite_daggers")))) {
				damage = 7;
			}
			if (damage > 0) {
				if (itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0) {
					damage = damage + 0.5 + 0.5 * itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS));
				}
				tooltip.add(Component.literal(("\u00A72 " + new java.text.DecimalFormat("##.##").format(damage * percent) + " Spin Attack Damage")));
			} else {
				tooltip.add(Component.literal("\u00A77Spin Attack Damage:"));
				tooltip.add(Component.literal(("\u00A79 " + new java.text.DecimalFormat("##.##").format(percent * 100) + "% of Weapon Damage")));
			}
		}
	}
}