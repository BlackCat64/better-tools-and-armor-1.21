package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.items.IItemHandler;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

public class GetEquippedCuriosItemProcedure {
	public static ItemStack execute(LevelAccessor world, Entity entity, ItemStack curiosItem) {
		if (entity == null)
			return ItemStack.EMPTY;
		ItemStack found_item = ItemStack.EMPTY;
		if (entity instanceof Player player4) {
			IItemHandler inventory4 = BetterToolsMod.CuriosApiHelper.getCuriosInventory(player4);
			if (inventory4 != null) {
				for (int i = 0; i < inventory4.getSlots(); i++) {
					ItemStack itemstackiterator = inventory4.getStackInSlot(i);
					if (itemstackiterator.getItem() == curiosItem.getItem()) {
						found_item = itemstackiterator;
						break;
					}
				}
			}
		}
		return found_item;
	}
}