package net.mcreator.bettertoolsandarmor.procedures;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.SectionPos;

import net.mcreator.bettertoolsandarmor.BetterToolsMod;

import javax.annotation.Nullable;

@EventBusSubscriber(Dist.CLIENT)
public class CrystalliteSwordGoldSpinAttackOnAirProcedure {
	@SubscribeEvent
	public static void onLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
		PacketDistributor.sendToServer(new CrystalliteSwordGoldSpinAttackOnAirMessage());
		execute(event.getLevel(), event.getEntity());
	}

	@EventBusSubscriber
	public record CrystalliteSwordGoldSpinAttackOnAirMessage() implements CustomPacketPayload {
		public static final Type<CrystalliteSwordGoldSpinAttackOnAirMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(BetterToolsMod.MODID, "procedure_crystallite_sword_gold_spin_attack_on_air"));
		public static final StreamCodec<RegistryFriendlyByteBuf, CrystalliteSwordGoldSpinAttackOnAirMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, CrystalliteSwordGoldSpinAttackOnAirMessage message) -> {
		}, (RegistryFriendlyByteBuf buffer) -> new CrystalliteSwordGoldSpinAttackOnAirMessage());

		@Override
		public Type<CrystalliteSwordGoldSpinAttackOnAirMessage> type() {
			return TYPE;
		}

		public static void handleData(final CrystalliteSwordGoldSpinAttackOnAirMessage message, final IPayloadContext context) {
			if (context.flow() == PacketFlow.SERVERBOUND) {
				context.enqueueWork(() -> {
					if (!context.player().level().getChunkSource().hasChunk(SectionPos.blockToSectionCoord(context.player().getX()), SectionPos.blockToSectionCoord(context.player().getZ())))
						return;
					execute(context.player().level(), context.player());
				}).exceptionally(e -> {
					context.connection().disconnect(Component.literal(e.getMessage()));
					return null;
				});
			}
		}

		@SubscribeEvent
		public static void registerMessage(FMLCommonSetupEvent event) {
			BetterToolsMod.addNetworkMessage(CrystalliteSwordGoldSpinAttackOnAirMessage.TYPE, CrystalliteSwordGoldSpinAttackOnAirMessage.STREAM_CODEC, CrystalliteSwordGoldSpinAttackOnAirMessage::handleData);
		}
	}

	public static void execute(LevelAccessor world, Entity entity) {
		execute(null, world, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		CrystalliteSwordGoldSplashDamageProcedure.execute(world, entity, entity);
	}
}