package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.component.DataComponents;

public class ProgressiveToolsSetThresholdsProcedure {
	public static void execute(ItemStack itemstack) {
		if (itemstack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag().getDouble("threshold_1") <= 0 && itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:progressive_tools")))) {
			if (itemstack.is(ItemTags.create(ResourceLocation.parse("minecraft:pickaxes")))) {
				{
					final String _tagName = "threshold_1";
					final double _tagValue = 5000;
					CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
				}
				{
					final String _tagName = "threshold_2";
					final double _tagValue = 20000;
					CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
				}
			} else if (itemstack.is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))) {
				{
					final String _tagName = "threshold_1";
					final double _tagValue = 1000;
					CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
				}
				{
					final String _tagName = "threshold_2";
					final double _tagValue = 4000;
					CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
				}
			} else if (itemstack.is(ItemTags.create(ResourceLocation.parse("minecraft:shovels")))) {
				{
					final String _tagName = "threshold_1";
					final double _tagValue = 10000;
					CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
				}
				{
					final String _tagName = "threshold_2";
					final double _tagValue = 30000;
					CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
				}
			} else if (itemstack.is(ItemTags.create(ResourceLocation.parse("minecraft:hoes")))) {
				{
					final String _tagName = "threshold_1";
					final double _tagValue = 1000;
					CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
				}
				{
					final String _tagName = "threshold_2";
					final double _tagValue = 3000;
					CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putDouble(_tagName, _tagValue));
				}
			}
		}
	}
}