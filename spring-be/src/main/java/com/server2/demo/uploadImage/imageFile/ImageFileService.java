package com.server2.demo.uploadImage.imageFile;




import com.server2.demo.uploadImage.UploadImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageFileService {
    MultipartFile getImageAsMultipartFile(UploadImage uploadImage) throws IOException;
}