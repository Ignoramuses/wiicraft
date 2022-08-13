package io.github.ignoramuses.wiicraft;

import net.minecraft.network.FriendlyByteBuf;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.ServerPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.resources.ResourceLocation;

public class WiiCraft implements ModInitializer {
	public static final String ID = "wiicraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize(ModContainer mod) {
		NativeLoader.load();
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
			FriendlyByteBuf buf = PacketByteBufs.create();
			buf.writeBoolean(true);
			sender.sendPacket(WiimoteHandler.PERMISSION_PACKET, buf);
		});
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(ID, path);
	}
}
