package net.mcreator.bettertoolsandarmor.world.features;

import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import net.mcreator.bettertoolsandarmor.world.features.configurations.StructureFeatureConfiguration;

public class GiantBlueMushroomFeatureFeature extends StructureFeature {
	public GiantBlueMushroomFeatureFeature() {
		super(StructureFeatureConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<StructureFeatureConfiguration> context) {
		return super.place(context);
	}
}