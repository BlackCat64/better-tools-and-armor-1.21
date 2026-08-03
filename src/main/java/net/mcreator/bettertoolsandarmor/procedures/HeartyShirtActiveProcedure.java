package net.mcreator.bettertoolsandarmor.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class HeartyShirtActiveProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		double absorption_limit = 0;
		return (entity instanceof Player _plr ? _plr.getAbsorptionAmount() : 0) < (entity instanceof LivingEntity _livingEntity1 && _livingEntity1.getAttributes().hasAttribute(Attributes.MAX_ABSORPTION)
				? _livingEntity1.getAttribute(Attributes.MAX_ABSORPTION).getValue()
				: 0);
	}
}