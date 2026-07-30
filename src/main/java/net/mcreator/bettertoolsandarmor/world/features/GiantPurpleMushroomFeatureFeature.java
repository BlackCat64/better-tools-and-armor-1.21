package net.mcreator.bettertoolsandarmor.world.features;

import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import net.mcreator.bettertoolsandarmor.world.features.configurations.StructureFeatureConfiguration;

public class GiantPurpleMushroomFeatureFeature extends StructureFeature {
	public GiantPurpleMushroomFeatureFeature() {
		super(StructureFeatureConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<StructureFeatureConfiguration> context) {
		return super.place(context);
	}
}