package com.anajoa.grape.domain.drive;

import java.nio.file.Path;

public final class DriveVideoFile extends DriveFile {

	private DriveVideoFile() {
	}

	public static DriveVideoFile of(Path path) {
		DriveVideoFile driveVideoFile = new DriveVideoFile();
		driveVideoFile.path = path;
		return driveVideoFile;
	}

	@Override
	public DriveResourceType getType() {
		return DriveResourceType.VIDEO;
	}

	@Override
	public Path getThumbnail() {
		throw new IllegalArgumentException("Thumbnail is not provided.");
	}

}
