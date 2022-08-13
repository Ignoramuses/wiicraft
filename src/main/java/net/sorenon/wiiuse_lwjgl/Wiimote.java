package net.sorenon.wiiuse_lwjgl;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.Struct;

import java.nio.ByteBuffer;

public class Wiimote extends Struct implements NativeResource {


	protected Wiimote(long address, @Nullable ByteBuffer container) {
		super(address, container);

		Layout layout_win32 = __struct(
				__member(4), //unid
				__member(8), //dev_handle
				__member(32), //hid_overlap
				__member(4), //stack
				__member(4), //timeout
				__member(1), //normal_timeout
				__member(1), //exp_timeout
				__member(4), //state
				__member(1), //leds
				__member(4), //battery_level
				__member(4) //flags
				//TODO
		);
	}

	@Override
	public int sizeof() {
		return 0;
	}
}
