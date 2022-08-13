package net.sorenon.wiiuse_lwjgl;

import org.lwjgl.system.Library;
import org.lwjgl.system.SharedLibrary;

import static org.lwjgl.system.APIUtil.apiGetFunctionAddress;
import static org.lwjgl.system.JNI.*;

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
				wiiuse_jni_get_state = apiGetFunctionAddress(WIIUSE, "wiiuse_jni_get_state"),
				wiiuse_jni_get_event = apiGetFunctionAddress(WIIUSE, "wiiuse_jni_get_event"),
				wiiuse_jni_wiimote_buttons = apiGetFunctionAddress(WIIUSE, "wiiuse_jni_wiimote_buttons"),
				wiiuse_poll = apiGetFunctionAddress(WIIUSE, "wiiuse_poll"),
				wiiuse_connect = apiGetFunctionAddress(WIIUSE, "wiiuse_connect");
	}

	public static final int WIIMOTE_LED_NONE = 0x00;
	public static final int WIIMOTE_LED_1 = 0x10;
	public static final int WIIMOTE_LED_2 = 0x20;
	public static final int WIIMOTE_LED_3 = 0x40;
	public static final int WIIMOTE_LED_4 = 0x80;

	public static final int WIIMOTE_STATE_CONNECTED = 0x0008;

	public static final int WIIMOTE_BUTTON_TWO = 0x0001;
	public static final int WIIMOTE_BUTTON_ONE = 0x0002;
	public static final int WIIMOTE_BUTTON_B = 0x0004;
	public static final int WIIMOTE_BUTTON_A = 0x0008;
	public static final int WIIMOTE_BUTTON_MINUS = 0x0010;
	public static final int WIIMOTE_BUTTON_ZACCEL_BIT6 = 0x0020;
	public static final int WIIMOTE_BUTTON_ZACCEL_BIT7 = 0x0040;
	public static final int WIIMOTE_BUTTON_HOME = 0x0080;
	public static final int WIIMOTE_BUTTON_LEFT = 0x0100;
	public static final int WIIMOTE_BUTTON_RIGHT = 0x0200;
	public static final int WIIMOTE_BUTTON_DOWN = 0x0400;
	public static final int WIIMOTE_BUTTON_UP = 0x0800;
	public static final int WIIMOTE_BUTTON_PLUS = 0x1000;
	public static final int WIIMOTE_BUTTON_ZACCEL_BIT4 = 0x2000;
	public static final int WIIMOTE_BUTTON_ZACCEL_BIT5 = 0x4000;
	public static final int WIIMOTE_BUTTON_UNKNOWN = 0x8000;
	public static final int WIIMOTE_BUTTON_ALL = 0x1F9F;

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

	public static int wiiuse_jni_get_state(long wm) {
		long __functionAddress = Functions.wiiuse_jni_get_state;
		return invokePI(wm, __functionAddress);
	}

	public static int wiiuse_jni_get_event(long wm) {
		long __functionAddress = Functions.wiiuse_jni_get_event;
		return invokePI(wm, __functionAddress);
	}

	public static int wiiuse_jni_wiimote_buttons(long wm) {
		long __functionAddress = Functions.wiiuse_jni_wiimote_buttons;
		return invokePI(wm, __functionAddress);
	}

	public static int wiiuse_poll(long wiimotes, int num) {
		long __functionAddress = Functions.wiiuse_poll;
		return invokePI(wiimotes, num, __functionAddress);
	}
}
