package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

public class TopazOreGenerationConditionProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z) {
		return y <= 75 && world.getBiome(BlockPos.containing(x, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) x, (int) z), z)).is(TagKey.create(Registries.BIOME, ResourceLocation.parse("c:is_desert")));
	}
}