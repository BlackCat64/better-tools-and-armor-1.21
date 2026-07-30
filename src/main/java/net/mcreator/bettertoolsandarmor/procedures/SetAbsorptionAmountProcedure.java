package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class SetAbsorptionAmountProcedure {
	public static void execute(Entity entity, double amount) {
		if (entity != null && entity instanceof LivingEntity living) {
			living.setAbsorptionAmount((float) amount);
		}
	}
}