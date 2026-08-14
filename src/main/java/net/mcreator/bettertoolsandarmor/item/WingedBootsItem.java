package net.mcreator.bettertoolsandarmor.item;

import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
import net.minecraft.Util;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import java.util.List;
import java.util.EnumMap;

@EventBusSubscriber
public abstract class WingedBootsItem extends ArmorItem {
	public static Holder<ArmorMaterial> ARMOR_MATERIAL = null;

	@SubscribeEvent
	public static void registerArmorMaterial(RegisterEvent event) {
		event.register(Registries.ARMOR_MATERIAL, registerHelper -> {
			ArmorMaterial armorMaterial = new ArmorMaterial(Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
				map.put(ArmorItem.Type.BOOTS, 4);
				map.put(ArmorItem.Type.LEGGINGS, 6);
				map.put(ArmorItem.Type.CHESTPLATE, 8);
				map.put(ArmorItem.Type.HELMET, 4);
				map.put(ArmorItem.Type.BODY, 8);
			}), 20, DeferredHolder.create(Registries.SOUND_EVENT, ResourceLocation.parse("block.amethyst_block.step")),
					() -> Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get())), List.of(new ArmorMaterial.Layer(ResourceLocation.parse("better_tools:winged_"))), 2.5f,
					0.125f);
			registerHelper.register(ResourceLocation.parse("better_tools:winged_boots"), armorMaterial);
			ARMOR_MATERIAL = BuiltInRegistries.ARMOR_MATERIAL.wrapAsHolder(armorMaterial);
		});
	}

	public WingedBootsItem(ArmorItem.Type type, Item.Properties properties) {
		super(ARMOR_MATERIAL, type, properties);
	}

	public static class Boots extends WingedBootsItem {
		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(35)).fireResistant()
					.attributes(ItemAttributeModifiers.builder().add(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.boots"), 4, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.FEET)
							.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.boots"), 2.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.FEET)
							.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.boots"), 0.125, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.FEET)
							.add(Attributes.SAFE_FALL_DISTANCE, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(BetterToolsMod.MODID, "winged_boots_0.boots"), 2, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.FEET).build()));
		}
	}
}