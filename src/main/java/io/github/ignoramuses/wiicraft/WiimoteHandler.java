package io.github.ignoramuses.wiicraft;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;

@Environment(EnvType.CLIENT)
public class WiimoteHandler {
	public static final ResourceLocation PERMISSION_PACKET = WiiCraft.id("permission");
	public static final WiiUseApiManager WII_API = new WiiUseApiManager();
	public static boolean ALLOWED = false;

	private static Wiimote wiimote;

	public static void init() {
	}

	public static void tick(Minecraft mc) {
		if (!ALLOWED) {
			return;
		}
		setupWiimote();
	}

	private static void setupWiimote() {
		if (wiimote != null) {
			return;
		}
		Wiimote[] wiimotes = WII_API.getWiimotes(1);
		if (wiimotes == null || wiimotes.length != 1) {
			return;
		}
		System.out.println("wiimote found!");
		wiimote = wiimotes[0];
		wiimote.setLeds(true, false, false, false);
		wiimote.activateContinuous();
		wiimote.activateIRTRacking();
		wiimote.activateMotionSensing();
		wiimote.activateSmoothing();
	}
}
