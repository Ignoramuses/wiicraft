package io.github.ignoramuses.wiicraft;

import org.apache.commons.io.FileUtils;
import org.quiltmc.loader.api.QuiltLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;

public class NativeLoader {
	public static void load() {
		final var fileName = "wiiuse.dll";
		final var nativesFolder = QuiltLoader.getGameDir().resolve("natives/");
		final var url = NativeLoader.class.getResource("/natives/" + fileName);

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
		} catch (IOException | NoSuchElementException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to load wiiuse natives.");
		}
	}
}
