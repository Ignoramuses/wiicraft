package io.github.ignoramuses.wiicraft;

import net.minecraft.network.chat.Component;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientTickEvents;
import org.quiltmc.qsl.lifecycle.api.client.event.ClientWorldTickEvents;
import org.quiltmc.qsl.networking.api.client.ClientPlayConnectionEvents;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public class WiiCraftClient implements ClientModInitializer {
	public static final Component DISALLOWED = Component.translatable("wiicraft.disabled");
	private static boolean sentMessage = false;

	@Override
	public void onInitializeClient(ModContainer mod) {
		ClientPlayNetworking.registerGlobalReceiver(WiimoteHandler.PERMISSION_PACKET, ((client, handler, buf, responseSender) -> {
			WiimoteHandler.ALLOWED = buf.readBoolean();
		}));
		ClientPlayConnectionEvents.INIT.register((handler, client) -> {
			WiimoteHandler.ALLOWED = false;
			sentMessage = false;
		});
		ClientWorldTickEvents.START.register((mc, level) -> {
			if (sentMessage || WiimoteHandler.ALLOWED)
				return;
			mc.player.sendSystemMessage(DISALLOWED);
			sentMessage = true;
		});
		ClientTickEvents.START.register(WiimoteHandler::tick);
		WiimoteHandler.init();
	}
}
