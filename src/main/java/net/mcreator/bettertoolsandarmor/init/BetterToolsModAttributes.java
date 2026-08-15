/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bettertoolsandarmor.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

@EventBusSubscriber
public class BetterToolsModAttributes {
	public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, BetterToolsMod.MODID);
	public static final DeferredHolder<Attribute, Attribute> CRITICAL_HIT_MULTIPLIER = REGISTRY.register("critical_hit_multiplier", () -> new RangedAttribute("attribute.better_tools.critical_hit_multiplier", 1.5d, 0d, 10d).setSyncable(true));
	public static final DeferredHolder<Attribute, Attribute> THORNS_DAMAGE = REGISTRY.register("thorns_damage", () -> new RangedAttribute("attribute.better_tools.thorns_damage", 0d, 0d, 20d).setSyncable(true));
	public static final DeferredHolder<Attribute, Attribute> FREEZE_THORNS_CHANCE = REGISTRY.register("freeze_thorns_chance", () -> new RangedAttribute("attribute.better_tools.freeze_thorns_chance", 0d, 0d, 1d).setSyncable(true));
	public static final DeferredHolder<Attribute, Attribute> FREEZE_THORNS_TIME = REGISTRY.register("freeze_thorns_time", () -> new RangedAttribute("attribute.better_tools.freeze_thorns_time", 0d, 0d, 20000d).setSyncable(true));
	public static final DeferredHolder<Attribute, Attribute> ATTACK_FREEZE_CHANCE = REGISTRY.register("attack_freeze_chance", () -> new RangedAttribute("attribute.better_tools.attack_freeze_chance", 0d, 0d, 1d).setSyncable(true));
	public static final DeferredHolder<Attribute, Attribute> ATTACK_FREEZE_TIME = REGISTRY.register("attack_freeze_time", () -> new RangedAttribute("attribute.better_tools.attack_freeze_time", 0d, 0d, 20000d).setSyncable(true));
	public static final DeferredHolder<Attribute, Attribute> LIGHTNING_THORNS_CHANCE = REGISTRY.register("lightning_thorns_chance", () -> new RangedAttribute("attribute.better_tools.lightning_thorns_chance", 0d, 0d, 1d).setSyncable(true));
	public static final DeferredHolder<Attribute, Attribute> EXTRA_JUMPS = REGISTRY.register("extra_jumps", () -> new RangedAttribute("attribute.better_tools.extra_jumps", 0d, 0d, 10d).setSyncable(true));

	@SubscribeEvent
	public static void addAttributes(EntityAttributeModificationEvent event) {
		event.add(EntityType.PLAYER, CRITICAL_HIT_MULTIPLIER);
		event.getTypes().forEach(entity -> event.add(entity, THORNS_DAMAGE));
		event.getTypes().forEach(entity -> event.add(entity, FREEZE_THORNS_CHANCE));
		event.getTypes().forEach(entity -> event.add(entity, FREEZE_THORNS_TIME));
		event.getTypes().forEach(entity -> event.add(entity, ATTACK_FREEZE_CHANCE));
		event.getTypes().forEach(entity -> event.add(entity, ATTACK_FREEZE_TIME));
		event.getTypes().forEach(entity -> event.add(entity, LIGHTNING_THORNS_CHANCE));
		event.add(EntityType.PLAYER, EXTRA_JUMPS);
	}
}