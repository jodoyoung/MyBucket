package com.anajoa.grape.domain.drive;

import java.nio.file.Files;
import java.nio.file.Path;

public final class DriveDirectory extends DriveResource {

	private DriveDirectory() {
	}

	public static DriveDirectory of(Path path) {
		if (!Files.isDirectory(path)) {
			throw new IllegalArgumentException("Not directory path. " + path.toString());
		}

		DriveDirectory driveDirectory = new DriveDirectory();
		driveDirectory.path = path;
		return driveDirectory;
	}

	@Override
	public DriveResourceType getType() {
		return DriveResourceType.DIRECTORY;
	}

	@Override
	public Path getThumbnail() {
		throw new IllegalArgumentException("Thumbnail is not provided.");
	}

}
