package com.anajoa.grape.server.drive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class DriveFile extends DriveResource {

	public static DriveFile of(Path path) {
		if (Files.isDirectory(path)) {
			throw new IllegalArgumentException("Path is directory. " + path.toString());
		}

		String contentType;
		try {
			contentType = Files.probeContentType(path);
			// TODO probe improve
		} catch (IOException e) {
			throw new IllegalArgumentException("File content type probe failed. " + path.toString(), e);
		}
		
		if (contentType == null) {
			return DriveBinaryFile.of(path);
		} else if (contentType.contains("image")) {
			return DriveImageFile.of(path);
		} else if (contentType.contains("video")) {
			return DriveVideoFile.of(path);
		} else {
			return DriveBinaryFile.of(path);
		}
	}

}
