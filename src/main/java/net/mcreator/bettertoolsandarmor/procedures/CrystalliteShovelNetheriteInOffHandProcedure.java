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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.ItemTags;
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
public class CrystalliteShovelNetheriteInOffHandProcedure {
	@SubscribeEvent
	public static void onRightClick(PlayerInteractEvent.RightClickEmpty event) {
		if (event.getHand() != InteractionHand.MAIN_HAND)
			return;
		PacketDistributor.sendToServer(new CrystalliteShovelNetheriteInOffHandMessage());
		execute(event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
	}

	@EventBusSubscriber
	public record CrystalliteShovelNetheriteInOffHandMessage() implements CustomPacketPayload {
		public static final Type<CrystalliteShovelNetheriteInOffHandMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(BetterToolsMod.MODID, "procedure_crystallite_shovel_netherite_in_off_hand"));
		public static final StreamCodec<RegistryFriendlyByteBuf, CrystalliteShovelNetheriteInOffHandMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, CrystalliteShovelNetheriteInOffHandMessage message) -> {
		}, (RegistryFriendlyByteBuf buffer) -> new CrystalliteShovelNetheriteInOffHandMessage());

		@Override
		public Type<CrystalliteShovelNetheriteInOffHandMessage> type() {
			return TYPE;
		}

		public static void handleData(final CrystalliteShovelNetheriteInOffHandMessage message, final IPayloadContext context) {
			if (context.flow() == PacketFlow.SERVERBOUND) {
				context.enqueueWork(() -> {
					if (!context.player().level().getChunkSource().hasChunk(SectionPos.blockToSectionCoord(context.player().getX()), SectionPos.blockToSectionCoord(context.player().getZ())))
						return;
					execute(context.player().level(), context.player().getX(), context.player().getY(), context.player().getZ(), context.player());
				}).exceptionally(e -> {
					context.connection().disconnect(Component.literal(e.getMessage()));
					return null;
				});
			}
		}

		@SubscribeEvent
		public static void registerMessage(FMLCommonSetupEvent event) {
			BetterToolsMod.addNetworkMessage(CrystalliteShovelNetheriteInOffHandMessage.TYPE, CrystalliteShovelNetheriteInOffHandMessage.STREAM_CODEC, CrystalliteShovelNetheriteInOffHandMessage::handleData);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).is(ItemTags.create(ResourceLocation.parse("better_tools:wither_curing_tools")))) {
			CrystalliteNetheriteShovelRemoveWitherProcedure.execute(world, x, y, z, entity, entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY);
		}
	}
}