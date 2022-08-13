package io.github.ignoramuses.wiicraft;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.resources.ResourceLocation;
import net.sorenon.wiiuse_lwjgl.Wiimotes;
import net.sorenon.wiiuse_lwjgl.Wiiuse;

public class WiiCraft implements ModInitializer {
	public static final String ID = "wiicraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	public static Wiimotes wiimotes;

	@Override
	public void onInitialize(ModContainer mod) {
		wiimotes = Wiimotes.init(1);

		System.out.println(wiimotes);
		var res = wiimotes.find(1);
		System.out.println(wiimotes + "_" + res);
		if (res != 0) {
			var res2 = wiimotes.connect();
			System.out.println(wiimotes + "_" + res + "_" + res2 + "!");

			var wiimote = new Wiimote(wiimotes.getWiimote(0));

			wiimote.setLEDs(Wiiuse.WIIMOTE_LED_2 + Wiiuse.WIIMOTE_LED_3);
			wiimote.setRumble(1);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			wiimote.setRumble(0);

			wiimote.enableIR();

			while (wiimote.isConnected()) {
				if (wiimotes.poll() != 0) {
					wiimote.handle_event();
				}
			}
		}


		LOGGER.info("hello from " + this.getClass().getName());
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(ID, path);
	}
}
