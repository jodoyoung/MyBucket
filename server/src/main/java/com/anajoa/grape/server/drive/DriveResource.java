package com.anajoa.grape.server.drive;

import java.nio.file.Files;
import java.nio.file.Path;

public abstract class DriveResource {

	protected Path path;

	public Path getPath() {
		return path;
	}

	public abstract DriveResourceType getType();

	public abstract Path getThumbnail();

	public static DriveResource of(Path path) {
		if (Files.notExists(path)) {
			throw new IllegalStateException("No such file. " + path.toString());
		}

		DriveResource resource;
		if (Files.isDirectory(path)) {
			resource = DriveDirectory.of(path);
		} else {
			resource = DriveFile.of(path);
		}
		return resource;
	}
}