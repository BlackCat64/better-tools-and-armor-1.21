package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.ModList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
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
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

@EventBusSubscriber
public class CrystalliteHoePrismarineProcedureProcedure {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() != InteractionHand.MAIN_HAND)
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		BlockState current = Blocks.AIR.defaultBlockState();
		double sx = 0;
		double sz = 0;
		double tilled = 0;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:hydration_hoes")))
				|| (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:hydration_hoes")))
						&& (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()) {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(ResourceLocation.parse("better_tools:hoe_allowed_blocks")))
					|| (world.getBlockState(BlockPos.containing(x, y, z))).is(BlockTags.create(ResourceLocation.parse("better_tools:farmland")))) {
				if (event instanceof ICancellableEvent _cancellable) {
					_cancellable.setCanceled(true);
				}
				sx = -1;
				sz = -1;
				tilled = 0;
				for (int index70 = 0; index70 < 3; index70++) {
					for (int index71 = 0; index71 < 3; index71++) {
						current = (world.getBlockState(BlockPos.containing(x + sx, y, z + sz)));
						if (world.isEmptyBlock(BlockPos.containing(x + sx, y + 1, z + sz))) {
							if (current.getBlock() == Blocks.FARMLAND && ModList.get().isLoaded("aquaculture") || current.is(BlockTags.create(ResourceLocation.parse("better_tools:hoe_allowed_blocks")))) {
								if (ModList.get().isLoaded("aquaculture")) {
									world.setBlock(BlockPos.containing(x + sx, y, z + sz), BuiltInRegistries.BLOCK.get(ResourceLocation.parse("aquaculture:farmland")).defaultBlockState(), 3);
								} else {
									world.setBlock(BlockPos.containing(x + sx, y, z + sz), Blocks.FARMLAND.defaultBlockState(), 3);
									{
										int _value = 7;
										BlockPos _pos = BlockPos.containing(x + sx, y, z + sz);
										BlockState _bs = world.getBlockState(_pos);
										if (_bs.getBlock().getStateDefinition().getProperty("moisture") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
											world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
									}
								}
								if (world instanceof Level _level)
									_level.updateNeighborsAt(BlockPos.containing(x + sx, y, z + sz), _level.getBlockState(BlockPos.containing(x + sx, y, z + sz)).getBlock());
								tilled = tilled + 1;
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x + sx, y, z + sz), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.hoe.till")), SoundSource.NEUTRAL, (float) 0.5, 1);
									} else {
										_level.playLocalSound((x + sx), y, (z + sz), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.hoe.till")), SoundSource.NEUTRAL, (float) 0.5, 1, false);
									}
								}
							}
						}
						sx = sx + 1;
					}
					sz = sz + 1;
					sx = -1;
				}
				if (tilled > 0) {
					if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:hydration_hoes")))) {
						if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
							if (world instanceof ServerLevel _level) {
								(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).hurtAndBreak((int) tilled, _level, null, _stkprov -> {
								});
							}
						}
						if (entity instanceof LivingEntity _entity)
							_entity.swing(InteractionHand.MAIN_HAND, true);
					} else {
						if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
							if (world instanceof ServerLevel _level) {
								(entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).hurtAndBreak((int) tilled, _level, null, _stkprov -> {
								});
							}
						}
						if (entity instanceof LivingEntity _entity)
							_entity.swing(InteractionHand.OFF_HAND, true);
					}
				}
			}
		}
	}
}