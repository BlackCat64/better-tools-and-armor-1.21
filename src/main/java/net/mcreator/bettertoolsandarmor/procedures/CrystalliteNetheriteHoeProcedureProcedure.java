package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteNetheriteHoeProcedureProcedure {
	@SubscribeEvent
	public static void onBlockBreaking(PlayerEvent.BreakSpeed event) {
		if (event.getPosition().isEmpty())
			return;
		execute(event, event.getEntity().level(), event.getPosition().get().getX(), event.getPosition().get().getY(), event.getPosition().get().getZ(), event.getEntity(), event.getNewSpeed());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, double breakSpeed) {
		execute(null, world, x, y, z, entity, breakSpeed);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, double breakSpeed) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:mines_nether_wood_instantly")))
				&& !(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)
				&& ((world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(ResourceLocation.parse("minecraft:warped_stems")))
						|| (world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(ResourceLocation.parse("minecraft:crimson_stems")))
						|| (world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(ResourceLocation.parse("minecraft:wart_blocks")))
						|| (world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(ResourceLocation.parse("better_tools:crimson_planks")))
						|| (world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(ResourceLocation.parse("better_tools:warped_planks"))) || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.SHROOMLIGHT)) {
			if (event instanceof PlayerEvent.BreakSpeed _speed15)
				_speed15.setNewSpeed((float) (breakSpeed * 1000));
		}
	}
}