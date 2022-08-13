package io.github.ignoramuses.wiicraft;

import net.minecraft.resources.ResourceLocation;

import net.sorenon.wiiuse_lwjgl.Wiimotes;
import net.sorenon.wiiuse_lwjgl.Wiiuse;

import net.sorenon.wiiuse_lwjgl.WiiuseEventType;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WiiCraft implements ModInitializer {
	public static final String ID = "wiicraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	public static Wiimotes WIIMOTES;

	@Override
	public void onInitialize(ModContainer mod) {
		WIIMOTES = Wiiuse.init(1);

		while (true) {
			System.out.println(WIIMOTES);
			var res = Wiiuse.nwiiuse_find(WIIMOTES.addr, WIIMOTES.len, 1);
			System.out.println(WIIMOTES + "_" + res);
			if (res != 0) {
				var res2 = Wiiuse.nwiiuse_connect(WIIMOTES.addr, WIIMOTES.len);
				System.out.println(WIIMOTES + "_" + res + "_" + res2 + "!");
				Wiiuse.nwiiuse_set_leds(WIIMOTES.getWiimote(0), Wiiuse.WIIMOTE_LED_2 + Wiiuse.WIIMOTE_LED_3);
				Wiiuse.wiiuse_rumble(WIIMOTES.getWiimote(0), 1);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Wiiuse.wiiuse_rumble(WIIMOTES.getWiimote(0), 0);

				while ((Wiiuse.wiiuse_jni_get_state(WIIMOTES.getWiimote(0)) & Wiiuse.WIIMOTE_STATE_CONNECTED) == Wiiuse.WIIMOTE_STATE_CONNECTED) {
					if (Wiiuse.wiiuse_poll(WIIMOTES.addr, WIIMOTES.len) != 0) {
						int eventInt = Wiiuse.wiiuse_jni_get_event(WIIMOTES.getWiimote(0));
						var eventType = WiiuseEventType.values()[eventInt];

						switch (eventType) {
							case WIIUSE_EVENT -> {
								int buttons = Wiiuse.wiiuse_jni_wiimote_buttons(WIIMOTES.getWiimote(0));
								System.out.println(buttons + " event " + ((buttons & Wiiuse.WIIMOTE_BUTTON_A) == Wiiuse.WIIMOTE_BUTTON_A));
							}
							default -> {}
						}
					}
				}

				break;
			}
		}

		LOGGER.info("hello from " + this.getClass().getName());
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(ID, path);
	}
}
