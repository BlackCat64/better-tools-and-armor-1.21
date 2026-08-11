package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;

public class CheckForNetheriteTierToolProcedure {
	public static boolean execute(Entity entity) {
		if (entity instanceof LivingEntity livEnt) {
			ItemStack tool = livEnt.getMainHandItem();
			if (tool.getItem() instanceof TieredItem tiered) {
				Tier toolTier = tiered.getTier();
				if (toolTier != null) {
					TagKey<Block> incorrectBlocks = toolTier.getIncorrectBlocksForDrops();
					// If there are no incorrect blocks for this tool, or if the incorrect blocks are for a Netherite tool
					if (incorrectBlocks == null || incorrectBlocks.equals(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)) {
						return true;
					}
					// Last resort for modded tools which define a custom 'incorrect blocks' tag - Check if the tool can at least mine Ancient Debris
					// This assumes the mod uses the vanilla tags for tools below Netherite, and doesn't define custom 'incorrect blocks' tags for tools which are able to mine Ancient Debris but are below Netherite
					if (!incorrectBlocks.equals(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)) {
						BlockState testBlock = Blocks.ANCIENT_DEBRIS.defaultBlockState();
						return tool.isCorrectToolForDrops(testBlock);
					}
				}
			}
		}
		return false;
	}
}