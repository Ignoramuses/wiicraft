package io.github.ignoramuses.wiicraft;

import net.sorenon.wiiuse_lwjgl.Wiiuse;
import net.sorenon.wiiuse_lwjgl.WiiuseEventType;

public class Wiimote {

	public final long addr;

	public int buttonsState = 0;

	public Wiimote(long addr) {
		this.addr = addr;
	}

	public boolean isConnected() {
		return (Wiiuse.wiiuse_jni_get_state(addr) & Wiiuse.WIIMOTE_STATE_CONNECTED) == Wiiuse.WIIMOTE_STATE_CONNECTED;
	}

	public void setLEDs(int LEDs) {
		Wiiuse.nwiiuse_set_leds(addr, LEDs);
	}

	public void setRumble(int state) {
		Wiiuse.wiiuse_rumble(addr, state);
	}

	public void enableIR() {
		Wiiuse.wiiuse_set_ir(addr, 1);
	}

	public void disableIR() {
		Wiiuse.wiiuse_set_ir(addr, 0);
	}

	public void handle_event() {
			int eventInt = Wiiuse.wiiuse_jni_get_event(addr);
			var eventType = WiiuseEventType.values()[eventInt];

			switch (eventType) {
				case WIIUSE_EVENT -> {
					event();
				}
				default -> {}
			}

	}

	public void event() {
		int newState = Wiiuse.wiiuse_jni_wiimote_buttons(addr);
		System.out.println(newState + " event " + ((newState & Wiiuse.WIIMOTE_BUTTON_A) == Wiiuse.WIIMOTE_BUTTON_A));

		this.buttonsState = newState;
	}
}
