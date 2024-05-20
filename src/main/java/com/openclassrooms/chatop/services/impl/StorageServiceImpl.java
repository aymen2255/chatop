package com.openclassrooms.chatop.services.impl;

import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.openclassrooms.chatop.services.StorageService;

@Service
public class StorageServiceImpl implements StorageService {

	//TODO definir le path de l'upload dans une variable d'environnement
	private static final String IMAGE_DIRECTORY = "C:\\Users\\aymen\\workspace\\Developper_backend_Java_Spring\\uploads";
	
	public String savePicture(MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            throw new IOException("The uploaded file is empty.");
        }

        String imagePath = IMAGE_DIRECTORY + "/" + image.getOriginalFilename();
        File destinationFile = new File(imagePath);
        image.transferTo(destinationFile);

        return imagePath;
    }

}