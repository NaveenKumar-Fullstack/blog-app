package com.nextera.blogapp.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nextera.blogapp.services.FileService;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // ✅ Get the original file name (like "photo.jpg")
        String originalFilename = file.getOriginalFilename();

        // ✅ Validate filename
        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new IOException("Invalid file: no extension found in uploaded file name.");
        }

        // ✅ Extract extension safely
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));

        
        // ✅ Allow only specific image formats
        if (!ext.matches("\\.(jpg|jpeg|png|gif)$")) {
            throw new IOException("Only image files are allowed! (jpg, jpeg, png, gif)");
        }

        // ✅ Generate random unique name
        String randomID = UUID.randomUUID().toString();
        String fileName = randomID + ext;

        // ✅ Build full path
        String filePath = path + File.separator + fileName;

        // ✅ Create folder if it doesn’t exist
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // ✅ Copy file to the target location
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }

    @Override
    public InputStream getResource(String path, String filename) throws FileNotFoundException {
        String fullPath = path + File.separator + filename;
        return new FileInputStream(fullPath);
    }
}
