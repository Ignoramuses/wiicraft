package io.github.ignoramuses.wiicraft_test.gametest;

import io.github.ignoramuses.wiicraft_test.WiiCraftTest;
import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;

public class WiiCraftGameTest implements FabricGameTest {
	@GameTest(template = WiiCraftTest.ID + ":minecart_test")
	public void minecartTest(GameTestHelper helper) {
		helper.setBlock(2, 2, 0, Blocks.REDSTONE_BLOCK);
		helper.succeedWhenEntityPresent(EntityType.MINECART, 2, 2, 3);
	}
}
