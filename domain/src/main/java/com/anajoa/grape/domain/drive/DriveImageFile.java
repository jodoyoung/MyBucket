package com.anajoa.grape.domain.drive;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.file.FileSystemDirectory;
import com.drew.metadata.jpeg.JpegDirectory;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

public final class DriveImageFile extends DriveFile {

	private static final Logger logger = LoggerFactory.getLogger(DriveImageFile.class);

	private long fileSize;
	private int imageWidth;
	private int imageHeight;
	private int isoRating;
	private String fNumber;
	private Date dateTime;
	private String shutterSpeed;
	private String whiteBalanceMode;
	private String flash;
	private String cameraMake;
	private String cameraModel;

	private DriveImageFile() {
	}

	public static DriveImageFile of(Path path) {
		DriveImageFile driveImageFile = new DriveImageFile();
		driveImageFile.path = path;

		try {
			Metadata metadata = ImageMetadataReader.readMetadata(path.toFile());
			FileSystemDirectory fileSystemDirectory = metadata.getFirstDirectoryOfType(FileSystemDirectory.class);
			driveImageFile.fileSize = fileSystemDirectory.getLongObject(FileSystemDirectory.TAG_FILE_SIZE);

			JpegDirectory jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);
			driveImageFile.imageWidth = jpegDirectory.getInteger(JpegDirectory.TAG_IMAGE_WIDTH);
			driveImageFile.imageHeight = jpegDirectory.getInteger(JpegDirectory.TAG_IMAGE_HEIGHT);

			ExifSubIFDDirectory exifSubIFDDirectory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
			driveImageFile.isoRating = exifSubIFDDirectory.getInteger(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT);
			driveImageFile.fNumber = exifSubIFDDirectory.getDescription(ExifSubIFDDirectory.TAG_FNUMBER);
			driveImageFile.dateTime = exifSubIFDDirectory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
			driveImageFile.shutterSpeed = exifSubIFDDirectory.getDescription(ExifSubIFDDirectory.TAG_SHUTTER_SPEED);
			driveImageFile.whiteBalanceMode = exifSubIFDDirectory
					.getDescription(ExifSubIFDDirectory.TAG_WHITE_BALANCE_MODE);
			driveImageFile.flash = exifSubIFDDirectory.getDescription(ExifSubIFDDirectory.TAG_FLASH);

			ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
			driveImageFile.cameraMake = exifIFD0Directory.getDescription(ExifIFD0Directory.TAG_MAKE);
			driveImageFile.cameraModel = exifIFD0Directory.getDescription(ExifIFD0Directory.TAG_MODEL);
		} catch (Exception e) {
			logger.warn("Image file metadata extract failed. path: {}", path, e);
		}
		return driveImageFile;
	}

	@Override
	public DriveResourceType getType() {
		return DriveResourceType.IMAGE;
	}

	@Override
	public Path getThumbnail() {
		Path thumbnailDirectory = getPath().getParent().resolve(".thumbnail");
		Path thumbnail = thumbnailDirectory.resolve(getPath().getFileName());

		if (Files.exists(thumbnail)) {
			return thumbnail;
		}

		if (Files.notExists(thumbnailDirectory)) {
			try {
				Files.createDirectory(thumbnailDirectory);
			} catch (IOException e) {
				throw new IllegalStateException("Thumbnail directory create failed.", e);
			}
		} else {
			if (!Files.isDirectory(thumbnailDirectory)) {
				throw new IllegalStateException(
						"Expected an directory, but it was file. " + thumbnailDirectory.toString());
			}
		}

		try {
			BufferedImage image = ImageIO.read(getPath().toFile());
			BufferedImage resizeImage = Scalr.resize(image, 150);
			ImageIO.write(resizeImage, FilenameUtils.getExtension(thumbnail.toString()), thumbnail.toFile());
		} catch (IOException e) {
			throw new RuntimeException("Image resize failed.", e);
		}

		return thumbnail;
	}

	public long getFileSize() {
		return fileSize;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public int getIsoRating() {
		return isoRating;
	}

	public String getfNumber() {
		return fNumber;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public String getShutterSpeed() {
		return shutterSpeed;
	}

	public String getWhiteBalanceMode() {
		return whiteBalanceMode;
	}

	public String getFlash() {
		return flash;
	}

	public String getCameraMake() {
		return cameraMake;
	}

	public String getCameraModel() {
		return cameraModel;
	}

}
