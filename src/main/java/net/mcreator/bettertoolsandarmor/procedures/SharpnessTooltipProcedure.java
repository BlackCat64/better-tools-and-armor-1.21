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

import java.util.regex.Pattern;
import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class SharpnessTooltipProcedure {
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
		String tooltipText = "";
		double tooltipLine = 0;
		double damage = 0;
		if (itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) != 0 && !(itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:ender_titanium_weapons")))
				|| itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:flaming_weapons"))) && itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:nether_diamond_upgraded_crystallite_items")))
				|| itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:dark_damage_boost_weapons"))) || itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:water_damage_boost_weapons"))))) {
			tooltipLine = GetTooltipLineContainingProcedure.execute("Attack Damage", tooltip);
			if (tooltipLine >= 0) {
				tooltipText = tooltip.get((int) tooltipLine).getString();
				String _splitContent14 = Pattern.quote(" ");
				String _toSplit14 = tooltipText;
				String[] _array14 = _toSplit14.split(_splitContent14);
				if (_array14.length != 0) {
					for (String stringiterator : _array14) {
						try {
							damage = Integer.parseInt(stringiterator);
							break;
						} catch (Exception _exception) {
						}
					}
				}
				if (damage > 0) {
					damage = damage + 0.5 + itemstack.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)) * 0.5;
					ReplaceTooltipLineProcedure.execute(tooltipLine, "\u00A72 " + new java.text.DecimalFormat("##.##").format(damage) + " Attack Damage", tooltip);
				}
			}
		}
	}
}