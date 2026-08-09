package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystallitePickaxeAmethystOreLocationProcedure {
	@SubscribeEvent
	public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double radius = 0;
		double count = 0;
		double repeats = 0;
		ItemStack tool = ItemStack.EMPTY;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:ore_echolocation_tools")))) {
			tool = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
		} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:ore_echolocation_tools")))
				&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()) {
			tool = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY);
		}
		if (entity.isShiftKeyDown() && tool.is(ItemTags.create(ResourceLocation.parse("better_tools:ore_echolocation_tools")))) {
			found = false;
			radius = 0;
			for (int index1502 = 0; index1502 < 19; index1502++) {
				radius = radius + 1;
				if (radius == 1) {
					repeats = 1;
				}
				sx = radius * (-1);
				for (int index1503 = 0; index1503 < 2; index1503++) {
					sy = radius * (-1);
					for (int index1504 = 0; index1504 < (int) (radius * 2); index1504++) {
						sz = radius * (-1);
						for (int index1505 = 0; index1505 < (int) (radius * 2); index1505++) {
							if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BlockTags.create(ResourceLocation.parse("better_tools:loot_blocks")))) {
								found = true;
								break;
							}
							sz = sz + 1;
						}
						if (found == true) {
							break;
						}
						sy = sy + 1;
					}
					if (found == true) {
						break;
					}
					sx = radius;
				}
				if (found == true) {
					break;
				}
				sz = radius * (-1);
				for (int index1506 = 0; index1506 < 2; index1506++) {
					sx = 1 - radius;
					if (radius > 1) {
						repeats = (radius - 1) * 2;
					}
					for (int index1507 = 0; index1507 < (int) repeats; index1507++) {
						sy = radius * (-1);
						for (int index1508 = 0; index1508 < (int) (radius * 2); index1508++) {
							if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BlockTags.create(ResourceLocation.parse("better_tools:loot_blocks")))) {
								found = true;
								break;
							}
							sy = sy + 1;
						}
						if (found == true) {
							break;
						}
						sx = sx + 1;
					}
					if (found == true) {
						break;
					}
					sz = radius;
				}
				if (found == true) {
					break;
				}
				sy = radius * (-1);
				for (int index1509 = 0; index1509 < 2; index1509++) {
					sx = 1 - radius;
					if (radius > 1) {
						repeats = (radius - 1) * 2;
					}
					for (int index1510 = 0; index1510 < (int) repeats; index1510++) {
						sz = 1 - radius;
						if (radius > 1) {
							repeats = (radius - 1) * 2;
						}
						for (int index1511 = 0; index1511 < (int) repeats; index1511++) {
							if ((world.getBlockState(BlockPos.containing(x + sx, y + sy, z + sz))).is(BlockTags.create(ResourceLocation.parse("better_tools:loot_blocks")))) {
								found = true;
								break;
							}
							sz = sz + 1;
						}
						if (found == true) {
							break;
						}
						sx = sx + 1;
					}
					if (found == true) {
						break;
					}
					sy = radius;
				}
				if (found == true) {
					break;
				}
			}
			if (found == true) {
				if (entity instanceof ServerPlayer _player) {
					AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:ore_location_adv"));
					if (_adv != null) {
						AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
						if (!_ap.isDone()) {
							for (String criteria : _ap.getRemainingCriteria())
								_player.getAdvancements().award(_adv, criteria);
						}
					}
				}
				if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
					if (world instanceof ServerLevel _level) {
						tool.hurtAndBreak(20, _level, null, _stkprov -> {
						});
					}
					if (entity instanceof Player _player)
						_player.getCooldowns().addCooldown(tool.getItem(), 600);
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.amethyst_cluster.fall")), SoundSource.BLOCKS, 5, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.amethyst_cluster.fall")), SoundSource.BLOCKS, 5, 1, false);
					}
				}
				OreLocationParticlesProcedure.execute(world, x, y, z, x + sx + 0.5, y + sy + 0.5, z + sz + 0.5);
			} else {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.amethyst_block.fall")), SoundSource.BLOCKS, 5, (float) 0.2);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.amethyst_block.fall")), SoundSource.BLOCKS, 5, (float) 0.2, false);
					}
				}
				if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
					if (entity instanceof Player _player)
						_player.getCooldowns().addCooldown(tool.getItem(), 200);
				}
			}
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == tool.getItem()) {
				if (entity instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.MAIN_HAND, true);
			} else {
				if (entity instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.OFF_HAND, true);
			}
		}
	}
}