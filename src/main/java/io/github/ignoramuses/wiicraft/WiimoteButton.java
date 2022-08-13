package io.github.ignoramuses.wiicraft;

public enum WiimoteButton {
	TWO(0x0001),
	ONE(0x0002),
	B(0x0004),
	A(0x0008),
	MINUS(0x0010),
	HOME(0x0080),
	LEFT(0x0100),
	RIGHT(0x0200),
	DOWN(0x0400),
	UP(0x0800),
	PLUS(0x1000);

	public final int bitmask;

	WiimoteButton(int bitmask) {
		this.bitmask = bitmask;
	}

}
