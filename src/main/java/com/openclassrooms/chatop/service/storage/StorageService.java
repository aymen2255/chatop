package com.openclassrooms.chatop.service.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void init();
	String store(MultipartFile file);

}