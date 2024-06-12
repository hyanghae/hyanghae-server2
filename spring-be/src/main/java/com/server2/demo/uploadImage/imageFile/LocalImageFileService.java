package com.server2.demo.uploadImage.imageFile;


import com.server2.demo.uploadImage.UploadImage;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Profile({"local"})
@Service
public class LocalImageFileService implements ImageFileService {

    @Override
    public MultipartFile getImageAsMultipartFile(UploadImage uploadImage) throws IOException {
        File file = new File(uploadImage.getSavedImageUrl());
        byte[] fileContent = Files.readAllBytes(file.toPath());
        String contentType = Files.probeContentType(file.toPath());
        String originalFilename = file.getName();

        return new CustomMultipartFile(fileContent, originalFilename, "file", contentType);
    }
}