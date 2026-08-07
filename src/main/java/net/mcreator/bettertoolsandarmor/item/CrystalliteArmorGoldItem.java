package net.mcreator.bettertoolsandarmor.item;

import net.neoforged.neoforge.registries.RegisterEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Holder;
import net.minecraft.Util;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import java.util.List;
import java.util.EnumMap;

@EventBusSubscriber
public abstract class CrystalliteArmorGoldItem extends ArmorItem {
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
			}), 20, DeferredHolder.create(Registries.SOUND_EVENT, ResourceLocation.parse("better_tools:crystallite_step")),
					() -> Ingredient.of(new ItemStack(BetterToolsModItems.CRYSTALLITE_SHARDS.get()), new ItemStack(BetterToolsModItems.CRYSTALLITE_GEM.get()), new ItemStack(Items.GOLD_INGOT)),
					List.of(new ArmorMaterial.Layer(ResourceLocation.parse("better_tools:crystallite_gold_"))), 2.5f, 0.125f);
			registerHelper.register(ResourceLocation.parse("better_tools:crystallite_armor_gold"), armorMaterial);
			ARMOR_MATERIAL = BuiltInRegistries.ARMOR_MATERIAL.wrapAsHolder(armorMaterial);
		});
	}

	public CrystalliteArmorGoldItem(ArmorItem.Type type, Item.Properties properties) {
		super(ARMOR_MATERIAL, type, properties);
	}

	public static class Helmet extends CrystalliteArmorGoldItem {
		public Helmet() {
			super(ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(40)).fireResistant().attributes(ItemAttributeModifiers.builder()
					.add(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.helmet"), 4, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HEAD)
					.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.helmet"), 2.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HEAD)
					.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.helmet"), 0.125, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HEAD)
					.add(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(BetterToolsMod.MODID, "crystallite_armor_gold_0.helmet"), 1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HEAD)
					.build()));
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, context, list, flag);
			list.add(Component.translatable("item.better_tools.crystallite_armor_gold_helmet.description_0"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_gold_helmet.description_1"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_gold_helmet.description_2"));
		}

		@Override
		public boolean makesPiglinsNeutral(ItemStack itemstack, LivingEntity entity) {
			return true;
		}
	}

	public static class Chestplate extends CrystalliteArmorGoldItem {
		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE,
					new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(40)).fireResistant()
							.attributes(ItemAttributeModifiers.builder().add(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.chestplate"), 8, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
									.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.chestplate"), 2.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
									.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.chestplate"), 0.125, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.CHEST)
									.add(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(BetterToolsMod.MODID, "crystallite_armor_gold_0.chestplate"), 1, AttributeModifier.Operation.ADD_VALUE),
											EquipmentSlotGroup.CHEST)
									.build()));
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, context, list, flag);
			list.add(Component.translatable("item.better_tools.crystallite_armor_gold_chestplate.description_0"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_gold_chestplate.description_1"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_gold_chestplate.description_2"));
		}

		@Override
		public boolean makesPiglinsNeutral(ItemStack itemstack, LivingEntity entity) {
			return true;
		}
	}

	public static class Leggings extends CrystalliteArmorGoldItem {
		public Leggings() {
			super(ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(40)).fireResistant().attributes(ItemAttributeModifiers.builder()
					.add(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.leggings"), 6, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.LEGS)
					.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.leggings"), 2.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.LEGS)
					.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.leggings"), 0.125, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.LEGS)
					.add(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(BetterToolsMod.MODID, "crystallite_armor_gold_0.leggings"), 1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.LEGS)
					.build()));
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, context, list, flag);
			list.add(Component.translatable("item.better_tools.crystallite_armor_gold_leggings.description_0"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_gold_leggings.description_1"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_gold_leggings.description_2"));
		}

		@Override
		public boolean makesPiglinsNeutral(ItemStack itemstack, LivingEntity entity) {
			return true;
		}
	}

	public static class Boots extends CrystalliteArmorGoldItem {
		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(40)).fireResistant().attributes(ItemAttributeModifiers.builder()
					.add(Attributes.ARMOR, new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.boots"), 4, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.FEET)
					.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.boots"), 2.5, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.FEET)
					.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.boots"), 0.125, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.FEET)
					.add(Attributes.BLOCK_INTERACTION_RANGE, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(BetterToolsMod.MODID, "crystallite_armor_gold_0.boots"), 1, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.FEET)
					.build()));
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public void appendHoverText(ItemStack itemstack, Item.TooltipContext context, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, context, list, flag);
			list.add(Component.translatable("item.better_tools.crystallite_armor_gold_boots.description_0"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_gold_boots.description_1"));
			list.add(Component.translatable("item.better_tools.crystallite_armor_gold_boots.description_2"));
		}

		@Override
		public boolean makesPiglinsNeutral(ItemStack itemstack, LivingEntity entity) {
			return true;
		}
	}
}