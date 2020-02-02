package com.anajoa.grape.domain.drive;

import java.nio.file.Path;

public final class DriveBinaryFile extends DriveFile {

	private DriveBinaryFile() {
	}

	public static DriveBinaryFile of(Path path) {
		DriveBinaryFile driveBinaryFile = new DriveBinaryFile();
		driveBinaryFile.path = path;
		return driveBinaryFile;
	}

	@Override
	public DriveResourceType getType() {
		return DriveResourceType.BINARY;
	}

	@Override
	public Path getThumbnail() {
		throw new IllegalArgumentException("Thumbnail is not provided.");
	}

}
