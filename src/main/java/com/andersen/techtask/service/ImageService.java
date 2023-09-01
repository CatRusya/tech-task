package com.andersen.techtask.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

  String loadLogoFile(MultipartFile file);

  String getUrl(String logoPath);
}
