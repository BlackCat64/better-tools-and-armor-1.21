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
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

import java.util.List;

@EventBusSubscriber(value = {Dist.CLIENT})
public class SafeFallDistanceTooltipProcedure {
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
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("minecraft:foot_armor"))) && (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == itemstack.getItem()
				&& (entity instanceof LivingEntity _livingEntity5 && _livingEntity5.getAttributes().hasAttribute(Attributes.SAFE_FALL_DISTANCE)
						? _livingEntity5.getAttribute(Attributes.SAFE_FALL_DISTANCE).getValue()
						: 0) != (entity instanceof LivingEntity _livingEntity6 && _livingEntity6.getAttributes().hasAttribute(Attributes.SAFE_FALL_DISTANCE) ? _livingEntity6.getAttribute(Attributes.SAFE_FALL_DISTANCE).getBaseValue() : 0)) {
			ReplaceTooltipLineProcedure.execute(GetTooltipLineContainingProcedure.execute("Safe Fall Distance", tooltip),
					"\u00A72 "
							+ (new java.text.DecimalFormat("##.##")
									.format(entity instanceof LivingEntity _livingEntity7 && _livingEntity7.getAttributes().hasAttribute(Attributes.SAFE_FALL_DISTANCE) ? _livingEntity7.getAttribute(Attributes.SAFE_FALL_DISTANCE).getValue() : 0))
							+ " Safe Fall Distance",
					tooltip);
		}
	}
}