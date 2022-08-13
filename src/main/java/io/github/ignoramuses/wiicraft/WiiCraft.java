package io.github.ignoramuses.wiicraft;

import net.minecraft.resources.ResourceLocation;

import net.sorenon.wiiuse_lwjgl.Wiiuse;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WiiCraft implements ModInitializer {
	public static final String ID = "wiicraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize(ModContainer mod) {
		var wiimotes = Wiiuse.init(1);

		while (true) {
			System.out.println(wiimotes);
			var res = Wiiuse.nwiiuse_find(wiimotes.addr, wiimotes.len, 1);
			System.out.println(wiimotes + "_" + res);
			if (res != 0) {
				var res2 = Wiiuse.nwiiuse_connect(wiimotes.addr, wiimotes.len);
				System.out.println(wiimotes + "_" + res + "_" + res2 + "!");
				Wiiuse.nwiiuse_set_leds(wiimotes.getWiimote(0), Wiiuse.WIIMOTE_LED_2 + Wiiuse.WIIMOTE_LED_3);
				Wiiuse.wiiuse_rumble(wiimotes.getWiimote(0), 1);
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Wiiuse.wiiuse_rumble(wiimotes.getWiimote(0), 0);

				break;
			}
		}

		LOGGER.info("hello from " + this.getClass().getName());
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(ID, path);
	}
}
