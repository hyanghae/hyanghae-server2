package com.server2.demo.uploadImage;


import com.server2.demo.exception.BaseException;
import com.server2.demo.exception.BaseResponseCode;
import com.server2.demo.member.domain.Member;
import com.server2.demo.uploadImage.imageFile.ImageFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UploadImageService {

    private final UploadImageRepository uploadImageRepository;
    private final ImageFileService imageFileService;

    public MultipartFile getSettingImageFile(Member member) {

        UploadImage uploadImage = uploadImageRepository.findByMemberAndIsSetting(member, true)
                .orElseThrow(() -> new BaseException(BaseResponseCode.NO_IMAGE_EXCEPTION));

        return convertToMultipartFile(uploadImage);
    }

    public boolean isSettingImageExist(Member member) {
        return uploadImageRepository.findByMemberAndIsSetting(member, true).isPresent();
    }

    private MultipartFile convertToMultipartFile(UploadImage uploadImage) {
        try {
            return imageFileService.getImageAsMultipartFile(uploadImage);
        } catch (IOException e) {
            log.error("Error occurred 이미지 읽기 오류 image to MultipartFile: ", e);
            throw new BaseException(BaseResponseCode.IMAGE_PROCESSING_ERROR);
        }
    }
}
