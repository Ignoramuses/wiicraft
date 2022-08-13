package net.sorenon.wiiuse_lwjgl;

import static org.lwjgl.system.APIUtil.apiGetFunctionAddress;
import static org.lwjgl.system.JNI.callPI;
import static org.lwjgl.system.JNI.invokeJV;
import static org.lwjgl.system.JNI.invokeP;
import static org.lwjgl.system.JNI.invokePI;

import org.lwjgl.system.Library;
import org.lwjgl.system.SharedLibrary;

public class Wiiuse {
	private static final SharedLibrary WIIUSE = Library.loadNative(Wiiuse.class, "net.sorenon.wiiuse_lwjgl", "wiiuse.dll", true);

	public static final class Functions {

		private Functions() {
		}

		/**
		 * Function address.
		 */
		public static final long
				wiiuse_init = apiGetFunctionAddress(WIIUSE, "wiiuse_init"),
				wiiuse_find = apiGetFunctionAddress(WIIUSE, "wiiuse_find"),
				wiiuse_set_leds = apiGetFunctionAddress(WIIUSE, "wiiuse_set_leds"),
				wiiuse_rumble = apiGetFunctionAddress(WIIUSE, "wiiuse_rumble"),
				wiiuse_connect = apiGetFunctionAddress(WIIUSE, "wiiuse_connect");
	}

	public static final int WIIMOTE_LED_NONE = 0x00;
	public static final int WIIMOTE_LED_1 = 0x10;
	public static final int WIIMOTE_LED_2 = 0x20;
	public static final int WIIMOTE_LED_3 = 0x40;
	public static final int WIIMOTE_LED_4 = 0x80;

	public static Wiimotes init(int wiimotes) {
		long addr = nwiiuse_init(wiimotes);
		return new Wiimotes(addr, wiimotes);
	}

	public static long nwiiuse_init(int wiimotes) {
		long __functionAddress = Functions.wiiuse_init;
		return invokeP(wiimotes, __functionAddress);
	}

	public static int nwiiuse_find(long wiimotes, int maxWiimotes, int timeout) {
		long __functionAddress = Functions.wiiuse_find;
		return callPI(wiimotes, maxWiimotes, timeout, __functionAddress);
	}

	public static int nwiiuse_connect(long wiimotes, int maxWiimotes) {
		long __functionAddress = Functions.wiiuse_connect;
		return invokePI(wiimotes, maxWiimotes, __functionAddress);
	}

	public static void nwiiuse_set_leds(long wm, int leds) {
		long __functionAddress = Functions.wiiuse_set_leds;
		invokeJV(wm, leds, __functionAddress);
	}

	public static void wiiuse_rumble(long wm, int status) {
		long __functionAddress = Functions.wiiuse_rumble;
		invokeJV(wm, status, __functionAddress);
	}
}
