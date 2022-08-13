package io.github.ignoramuses.wiicraft;

import org.jetbrains.annotations.Nullable;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.resources.ResourceLocation;
import wiiusej.WiiUseApiManager;

public class WiiCraft implements ModInitializer {
	public static final String ID = "wiicraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Nullable
	public static WiiUseApiManager API_MAN = null;

	@Override
	public void onInitialize(ModContainer mod) {
		if (NativeLoader.load()) {
			API_MAN = new WiiUseApiManager();
		}

		LOGGER.info("hello from " + this.getClass().getName());
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(ID, path);
	}
}
