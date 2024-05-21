package com.openclassrooms.chatop.storage;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	String savePicture(MultipartFile image) throws IOException;

}