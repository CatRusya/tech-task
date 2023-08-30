package com.andersen.techtask.service;

import io.minio.GetObjectResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

//    String loadLogoFile(MultipartFile file) throws IOException;
//GetObjectResponse getLogoById(String logoId) throws Exception;
//
//    String loadLogoFile(MultipartFile multipartFile) throws Exception;
//
//    void deleteLogoFile(String previousLogoUuid) throws Exception;
    String loadLogoFile(MultipartFile file);
}
