package net.sorenon.wiiuse_lwjgl;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryUtil;

public class Wiimotes {

	public final long addr;
	public final int len;

	private final PointerBuffer buffer;

	public Wiimotes(long addr, int len) {
		this.addr = addr;
		this.len = len;
		this.buffer = MemoryUtil.memPointerBuffer(addr, len);
	}

	public long getWiimote(int num) {
		return this.buffer.get(num);
	}
}
