package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteNetheriteDeepslateProcedureProcedure {
	@SubscribeEvent
	public static void onBlockBreaking(PlayerEvent.BreakSpeed event) {
		if (event.getPosition().isEmpty())
			return;
		execute(event, event.getEntity().level(), event.getState(), event.getEntity(), event.getNewSpeed());
	}

	public static void execute(LevelAccessor world, BlockState blockstate, Entity entity, double breakSpeed) {
		execute(null, world, blockstate, entity, breakSpeed);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, BlockState blockstate, Entity entity, double breakSpeed) {
		if (entity == null)
			return;
		if ((blockstate.is(BlockTags.create(ResourceLocation.parse("c:stones"))) || blockstate.is(BlockTags.create(ResourceLocation.parse("better_tools:magma"))) || blockstate.getBlock() == Blocks.BASALT || blockstate.getBlock() == Blocks.BLACKSTONE)
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:deep_mining_tools")))
				&& !(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.EFFICIENCY)) != 0
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.EFFICIENCY)) >= 5) {
			if (event instanceof PlayerEvent.BreakSpeed _speed15)
				_speed15.setNewSpeed((float) (breakSpeed * 1000));
		}
	}
}