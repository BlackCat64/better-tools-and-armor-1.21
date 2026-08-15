package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.component.DataComponents;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

@EventBusSubscriber
public class WingedBootsExtraJumpsProcedure {
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent.Post event) {
		execute(event, event.getEntity().level(), event.getEntity());
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		ItemStack vial = ItemStack.EMPTY;
		if (entity instanceof LivingEntity _entity) {
			_entity.getAttribute(BetterToolsModAttributes.EXTRA_JUMPS).removeModifier(ResourceLocation.parse("better_tools:winged_boots"));
		}
		if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).getItem() == BetterToolsModItems.WINGED_BOOTS_BOOTS.get() && PlayerHasEnergyVialEquippedProcedure.execute(world, entity)) {
			vial = GetEquippedVialProcedure.execute(world, entity).copy();
			if (vial.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("energy") >= 50 && vial.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getBoolean("boots_active")) {
				if (entity instanceof LivingEntity _entity) {
					AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:winged_boots"), 1, AttributeModifier.Operation.ADD_VALUE);
					if (!_entity.getAttribute(BetterToolsModAttributes.EXTRA_JUMPS).hasModifier(modifier.id())) {
						_entity.getAttribute(BetterToolsModAttributes.EXTRA_JUMPS).addPermanentModifier(modifier);
					}
				}
			}
		}
	}
}