package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.items.IItemHandlerModifiable;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;

public class MigrateOldProgressiveToolsProcedure {
	public static void execute(Entity entity, ItemStack itemstack, double slot) {
		if (entity == null)
			return;
		ItemStack new_tool = ItemStack.EMPTY;
		String reg_name = "";
		if (itemstack.is(ItemTags.create(ResourceLocation.parse("better_tools:progressive_tools"))) && (BuiltInRegistries.ITEM.getKey(itemstack.getItem()).toString()).contains("_upgrade_")) {
			reg_name = ((BuiltInRegistries.ITEM.getKey(itemstack.getItem()).toString()).replace("_upgrade_2", "")).replace("_upgrade_1", "");
			new_tool = new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.parse((reg_name).toLowerCase(java.util.Locale.ENGLISH)))).copy();
			if (new_tool.is(ItemTags.create(ResourceLocation.parse("better_tools:progressive_tools")))) {
				new_tool.applyComponents(itemstack.getComponents());
				if (entity.getCapability(Capabilities.ItemHandler.ENTITY, null) instanceof IItemHandlerModifiable _modHandler) {
					ItemStack _setstack = new_tool.copy();
					_setstack.setCount(1);
					_modHandler.setStackInSlot((int) slot, _setstack);
				}
			}
		}
	}
}