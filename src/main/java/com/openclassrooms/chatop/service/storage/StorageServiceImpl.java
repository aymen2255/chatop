package com.openclassrooms.chatop.service.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class StorageServiceImpl implements StorageService {

	private final Path rootLocation;

	public StorageServiceImpl(StorageProperties properties) {

		if (properties.getLocation().trim().length() == 0) {
			throw new StorageException("File upload location can not be Empty.");
		}

		this.rootLocation = Paths.get(properties.getLocation());
	}

	private String createUniqueFileName(String fileName) {

		String fileExtension = FilenameUtils.getExtension(fileName);
		return UUID.randomUUID().toString() + "." + fileExtension;
	}

	@Override
	public String store(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}

			String uniqueFileName = createUniqueFileName(file.getOriginalFilename());

			Path destinationFile = this.rootLocation.resolve(Paths.get(uniqueFileName)).normalize().toAbsolutePath();

			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				// This is a security check
				throw new StorageException("Cannot store file outside current directory.");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}

			return ServletUriComponentsBuilder.fromCurrentContextPath()
					.pathSegment("images", destinationFile.getFileName().toString()).toUriString();

		} catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

}