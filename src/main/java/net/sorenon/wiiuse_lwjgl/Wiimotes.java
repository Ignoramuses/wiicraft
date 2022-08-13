package net.sorenon.wiiuse_lwjgl;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryUtil;

public class Wiimotes {

	public final long addr;
	public final int len;

	private final PointerBuffer buffer;

	public static Wiimotes init(int maxNum) {
		long addr = Wiiuse.nwiiuse_init(maxNum);
		return new Wiimotes(addr, maxNum);
	}

	public Wiimotes(long addr, int len) {
		this.addr = addr;
		this.len = len;
		this.buffer = MemoryUtil.memPointerBuffer(addr, len);
	}

	public int find(int timeout) {
		return Wiiuse.nwiiuse_find(addr, len, timeout);
	}

	public int connect() {
		return Wiiuse.nwiiuse_connect(addr, len);
	}

	public long getWiimote(int num) {
		return this.buffer.get(num);
	}

	public int poll() {
		return Wiiuse.wiiuse_poll(addr, len);
	}
}
