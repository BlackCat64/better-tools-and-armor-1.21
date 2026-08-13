package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModAttributes;

import javax.annotation.Nullable;

@EventBusSubscriber
public class SapphireArmorSetAttributesProcedure {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Pre event) {
		execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double armor_pieces = 0;
		double time = 0;
		double chance = 0;
		double i = 0;
		double BASE_CHANCE = 0;
		double CRYSTALLITE_CHANCE = 0;
		boolean crystallite = false;
		BASE_CHANCE = 0.04;
		CRYSTALLITE_CHANCE = 0.08;
		if (entity instanceof LivingEntity _livingEntity0 && _livingEntity0.getAttributes().hasAttribute(BetterToolsModAttributes.FREEZE_THORNS_CHANCE) && !world.isClientSide()) {
			for (int index350 = 0; index350 < 4; index350++) {
				if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(new Object() {
					public static EquipmentSlot armorSlotByIndex(int _slotindex) {
						for (EquipmentSlot _slot : EquipmentSlot.values()) {
							if (_slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR && _slot.getIndex() == _slotindex) {
								return _slot;
							}
						}
						throw new IllegalArgumentException("Invalid slot index: " + _slotindex);
					}
				}.armorSlotByIndex((int) i)) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:freezing_armor")))) {
					armor_pieces = armor_pieces + 1;
					if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(new Object() {
						public static EquipmentSlot armorSlotByIndex(int _slotindex) {
							for (EquipmentSlot _slot : EquipmentSlot.values()) {
								if (_slot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR && _slot.getIndex() == _slotindex) {
									return _slot;
								}
							}
							throw new IllegalArgumentException("Invalid slot index: " + _slotindex);
						}
					}.armorSlotByIndex((int) i)) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:upgraded_crystallite_armor")))) {
						chance = chance + CRYSTALLITE_CHANCE;
						crystallite = true;
					} else {
						chance = chance + BASE_CHANCE;
					}
				}
				i = i + 1;
			}
			time = crystallite ? 200 : 100;
			if (armor_pieces == 4) {
				chance = chance + (crystallite ? CRYSTALLITE_CHANCE : BASE_CHANCE);
			}
			if (IsInColdBiomeProcedure.execute(world, x, y, z)) {
				chance = chance * (crystallite ? 1.5 : 2);
				time = time * (crystallite ? 1.5 : 2);
			}
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(BetterToolsModAttributes.FREEZE_THORNS_CHANCE).removeModifier(ResourceLocation.parse("better_tools:sapphire_armor"));
			}
			if (entity instanceof LivingEntity _entity) {
				_entity.getAttribute(BetterToolsModAttributes.FREEZE_THORNS_TIME).removeModifier(ResourceLocation.parse("better_tools:sapphire_armor"));
			}
			if (chance > 0) {
				chance = chance + (entity instanceof LivingEntity _livingEntity8 && _livingEntity8.getAttributes().hasAttribute(Attributes.LUCK) ? _livingEntity8.getAttribute(Attributes.LUCK).getValue() : 0) * 0.05;
				if (HasCuriosItemEquippedProcedure.execute(world, entity, new ItemStack(BetterToolsModItems.ICY_BRACELET.get()))) {
					chance = chance + 0.1;
				}
				if (entity instanceof LivingEntity _entity) {
					AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:sapphire_armor"), chance, AttributeModifier.Operation.ADD_VALUE);
					if (!_entity.getAttribute(BetterToolsModAttributes.FREEZE_THORNS_CHANCE).hasModifier(modifier.id())) {
						_entity.getAttribute(BetterToolsModAttributes.FREEZE_THORNS_CHANCE).addPermanentModifier(modifier);
					}
				}
				if (entity instanceof LivingEntity _entity) {
					AttributeModifier modifier = new AttributeModifier(ResourceLocation.parse("better_tools:sapphire_armor"), time, AttributeModifier.Operation.ADD_VALUE);
					if (!_entity.getAttribute(BetterToolsModAttributes.FREEZE_THORNS_TIME).hasModifier(modifier.id())) {
						_entity.getAttribute(BetterToolsModAttributes.FREEZE_THORNS_TIME).addPermanentModifier(modifier);
					}
				}
			}
		}
	}
}