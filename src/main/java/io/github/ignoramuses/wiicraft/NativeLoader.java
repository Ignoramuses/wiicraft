package io.github.ignoramuses.wiicraft;

import java.io.IOException;
import java.nio.file.Files;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.lwjgl.system.Platform;
import org.quiltmc.loader.api.QuiltLoader;

public class NativeLoader {
	public static boolean load() {
		final var fileName = getFileName();
		if (fileName == null) {
			WiiCraft.LOGGER.error("Platform unknown");
			return false;
		}
		final var nativesFolder = QuiltLoader.getGameDir().resolve("natives/");
		final var url = NativeLoader.class.getResource("/natives/" + fileName);
		if (url == null) {
			WiiCraft.LOGGER.error("Platform not supported");
			return false;
		}

		try {
			if (!Files.exists(nativesFolder)) {
				Files.createDirectory(nativesFolder);
			}

			final var destination = nativesFolder.resolve(fileName);
			final var destinationFile = destination.toFile();

			if (Files.exists(destination)) {
				if (!destinationFile.delete()) {
					WiiCraft.LOGGER.warn("Failed to remove old wiiuse natives.");
				}
			}

			try {
				FileUtils.copyURLToFile(url, destinationFile);
			} catch (IOException e) {
				WiiCraft.LOGGER.warn("Unable to copy natives.");
			}

			System.load(destinationFile.getAbsolutePath());
			return true;
		} catch (IOException | NoSuchElementException e) {
			e.printStackTrace();
			WiiCraft.LOGGER.error("Unable to load wiiuse natives.");
			return false;
		}
	}

	public static String getFileName() {
		var platform = Platform.get();
		switch (platform) {
			case LINUX -> {
				return "libwiiuse.so";
			}
			case MACOSX -> {
				return "libwiiuse.dylib";
			}
			case WINDOWS -> {
				return "wiiuse.dll";
			}
			default -> {
				return null;
			}
		}
	}
}
