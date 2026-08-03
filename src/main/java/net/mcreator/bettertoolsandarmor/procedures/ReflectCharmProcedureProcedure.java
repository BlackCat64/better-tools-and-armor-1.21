package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.mcreator.bettertoolsandarmor.init.BetterToolsModItems;

import javax.annotation.Nullable;

@EventBusSubscriber
public class ReflectCharmProcedureProcedure {
	@SubscribeEvent
	public static void onEntityAttacked(LivingIncomingDamageEvent event) {
		if (event.getEntity() != null) {
			execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getSource().getDirectEntity(), event.getSource().getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity immediatesourceentity, Entity sourceentity) {
		execute(null, world, x, y, z, entity, immediatesourceentity, sourceentity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity immediatesourceentity, Entity sourceentity) {
		if (entity == null || immediatesourceentity == null || sourceentity == null)
			return;
		double projectile_speed = 0;
		Vec3 vector = Vec3.ZERO;
		if (HasCuriosItemEquippedProcedure.execute(world, entity, new ItemStack(BetterToolsModItems.REFLECT_CHARM.get())) && sourceentity instanceof LivingEntity && !(sourceentity == entity)
				&& !HasCuriosItemEquippedProcedure.execute(world, sourceentity, new ItemStack(BetterToolsModItems.REFLECT_CHARM.get()))) {
			vector = ((new Vec3((sourceentity.getX()), (sourceentity.getY() + sourceentity.getBbHeight() / 2d), (sourceentity.getZ()))).subtract((new Vec3((entity.getX()), (entity.getY() + entity.getBbHeight() / 2d), (entity.getZ()))))).normalize();
			projectile_speed = immediatesourceentity instanceof Projectile _projEnt ? _projEnt.getDeltaMovement().length() : 0;
			if (projectile_speed > 0) {
				if (immediatesourceentity instanceof Arrow) {
					if (event instanceof ICancellableEvent _cancellable) {
						_cancellable.setCanceled(true);
					}
					if (entity instanceof ServerPlayer _player) {
						AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:reflect_projectile_adv"));
						if (_adv != null) {
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
					if (immediatesourceentity.getRemainingFireTicks() > 0) {
						if (world instanceof ServerLevel projectileLevel) {
							Projectile _entityToSpawn = initArrowProjectile(new Arrow(projectileLevel, 0, 0, 0, new Arrow(EntityType.ARROW, projectileLevel).getPickupItemStackOrigin(), createArrowWeaponItemStack(projectileLevel, 1, (byte) 0)),
									entity, (float) (((Arrow) immediatesourceentity).getBaseDamage() + 2), false, true, false, AbstractArrow.Pickup.CREATIVE_ONLY);
							_entityToSpawn.setPos(x, (y + 2), z);
							_entityToSpawn.shoot((vector.x()), (vector.y()), (vector.z()), (float) projectile_speed, (float) 0.1);
							projectileLevel.addFreshEntity(_entityToSpawn);
						}
					} else {
						if (world instanceof ServerLevel projectileLevel) {
							Projectile _entityToSpawn = initArrowProjectile(new Arrow(projectileLevel, 0, 0, 0, new Arrow(EntityType.ARROW, projectileLevel).getPickupItemStackOrigin(), createArrowWeaponItemStack(projectileLevel, 1, (byte) 0)),
									entity, (float) (((Arrow) immediatesourceentity).getBaseDamage() + 2), false, false, false, AbstractArrow.Pickup.CREATIVE_ONLY);
							_entityToSpawn.setPos(x, (y + 2), z);
							_entityToSpawn.shoot((vector.x()), (vector.y()), (vector.z()), (float) projectile_speed, (float) 0.1);
							projectileLevel.addFreshEntity(_entityToSpawn);
						}
					}
					entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.ARROW), sourceentity), (float) ((Arrow) immediatesourceentity).getBaseDamage());
					if (!immediatesourceentity.level().isClientSide())
						immediatesourceentity.discard();
				} else if (immediatesourceentity instanceof LargeFireball) {
					if (event instanceof ICancellableEvent _cancellable) {
						_cancellable.setCanceled(true);
					}
					if (entity instanceof ServerPlayer _player) {
						AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:reflect_projectile_adv"));
						if (_adv != null) {
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
					if (!immediatesourceentity.level().isClientSide())
						immediatesourceentity.discard();
					if (world instanceof ServerLevel projectileLevel) {
						Projectile _entityToSpawn = initProjectileProperties(new LargeFireball(EntityType.FIREBALL, projectileLevel), entity, new Vec3((vector.x()), (vector.y()), (vector.z())));
						_entityToSpawn.setPos(x, (y + 2), z);
						_entityToSpawn.shoot((vector.x()), (vector.y()), (vector.z()), (float) projectile_speed, (float) 0.05);
						projectileLevel.addFreshEntity(_entityToSpawn);
					}
					entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.FIREBALL), sourceentity), 4);
				} else if (immediatesourceentity instanceof SmallFireball) {
					if (event instanceof ICancellableEvent _cancellable) {
						_cancellable.setCanceled(true);
					}
					if (entity instanceof ServerPlayer _player) {
						AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.parse("better_tools:reflect_projectile_adv"));
						if (_adv != null) {
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								for (String criteria : _ap.getRemainingCriteria())
									_player.getAdvancements().award(_adv, criteria);
							}
						}
					}
					if (!immediatesourceentity.level().isClientSide())
						immediatesourceentity.discard();
					if (world instanceof ServerLevel projectileLevel) {
						Projectile _entityToSpawn = initProjectileProperties(new SmallFireball(EntityType.SMALL_FIREBALL, projectileLevel), entity, new Vec3((vector.x()), (vector.y()), (vector.z())));
						_entityToSpawn.setPos(x, (y + 2), z);
						_entityToSpawn.shoot((vector.x()), (vector.y()), (vector.z()), (float) projectile_speed, (float) 0.05);
						projectileLevel.addFreshEntity(_entityToSpawn);
					}
					entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.FIREBALL), sourceentity), 2);
				}
			}
		}
	}

	private static AbstractArrow initArrowProjectile(AbstractArrow entityToSpawn, Entity shooter, float damage, boolean silent, boolean fire, boolean particles, AbstractArrow.Pickup pickup) {
		entityToSpawn.setOwner(shooter);
		entityToSpawn.setBaseDamage(damage);
		if (silent)
			entityToSpawn.setSilent(true);
		if (fire)
			entityToSpawn.igniteForSeconds(100);
		if (particles)
			entityToSpawn.setCritArrow(true);
		entityToSpawn.pickup = pickup;
		return entityToSpawn;
	}

	private static ItemStack createArrowWeaponItemStack(Level level, int knockback, byte piercing) {
		ItemStack weapon = new ItemStack(Items.ARROW);
		if (knockback > 0)
			weapon.enchant(level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK), knockback);
		if (piercing > 0)
			weapon.enchant(level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.PIERCING), piercing);
		return weapon;
	}

	private static Projectile initProjectileProperties(Projectile entityToSpawn, Entity shooter, Vec3 acceleration) {
		entityToSpawn.setOwner(shooter);
		if (!Vec3.ZERO.equals(acceleration)) {
			entityToSpawn.setDeltaMovement(acceleration);
			entityToSpawn.hasImpulse = true;
		}
		return entityToSpawn;
	}
}