package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingExperienceDropEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;
import net.mcreator.bettertoolsandarmor.init.BetterToolsModGameRules;

import javax.annotation.Nullable;

@EventBusSubscriber
public class ExperienceEnchProcedureProcedure {
	@SubscribeEvent
	public static void onLivingDropXp(LivingExperienceDropEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity(), event.getAttackingPlayer(), event.getDroppedExperience());
		}
	}

	public static void execute(LevelAccessor world, Entity entity, Entity sourceentity, double droppedexperience) {
		execute(null, world, entity, sourceentity, droppedexperience);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, Entity sourceentity, double droppedexperience) {
		if (entity == null || sourceentity == null)
			return;
		double xp = 0;
		double bonus_multiplier = 0;
		double pieces = 0;
		xp = droppedexperience;
		if ((sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
				.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("better_tools:experience_ench")))) != 0) {
			xp = xp + (sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY)
					.getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.parse("better_tools:experience_ench")))) * 5;
		}
		if (HasCuriosItemEquippedProcedure.execute(world, sourceentity, new ItemStack(BetterToolsModItems.MAGIC_RING.get()))) {
			pieces = pieces + 1;
		}
		if ((sourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:respawn_with_xp_armor")))) {
			pieces = pieces + 1;
		}
		if ((sourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:respawn_with_xp_armor")))) {
			pieces = pieces + 1;
		}
		if ((sourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:respawn_with_xp_armor")))) {
			pieces = pieces + 1;
		}
		if ((sourceentity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:respawn_with_xp_armor")))) {
			pieces = pieces + 1;
		}
		bonus_multiplier = pieces * 0.2;
		if (pieces >= 3) {
			bonus_multiplier = bonus_multiplier + 0.2;
		}
		xp = xp * (1 + bonus_multiplier);
		if (xp > droppedexperience) {
			if (event instanceof LivingExperienceDropEvent _event) {
				_event.setDroppedExperience((int) xp);
			}
		}
		if (world.getLevelData().getGameRules().getBoolean(BetterToolsModGameRules.DISPLAY_XP_DROP_VALUES) && sourceentity.hasPermissions(2) && sourceentity instanceof Player) {
			if (sourceentity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal((new java.text.DecimalFormat("##.##").format(xp) + " XP dropped by " + entity.getDisplayName().getString())), false);
		}
	}
}