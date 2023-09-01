package com.andersen.techtask.service.impl;

import com.andersen.techtask.exception.ImageUploadException;
import com.andersen.techtask.service.ImageService;
import com.andersen.techtask.service.props.MinioProperties;
import io.minio.*;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

  private final MinioClient minioClient;
  private final MinioProperties minioProperties;

  @Value("${minio.url}")
  private String imageUrl;

  @Value("${minio.bucket}")
  private String imageBucket;

  @Override
  public String loadLogoFile(MultipartFile file) {
    try {
      createBucket();
    } catch (Exception e) {
      throw new ImageUploadException("Image upload failed, there is no bucket: "
          + e.getMessage());
    }

    if (file.isEmpty() || file.getOriginalFilename() == null) {
      throw new ImageUploadException("Image must have name.");
    }

    String fileName = generateFileName(file);
    InputStream inputStream;
    try {
      inputStream = file.getInputStream();
    } catch (Exception e) {
      throw new ImageUploadException("Image upload failed: "
          + e.getMessage());
    }
    saveImage(inputStream, fileName);
    return fileName;
  }

  @Override
  public String getUrl(String logoPath) {
    if (Objects.isNull(logoPath))
      return null;
    return imageUrl + "/" + imageBucket + "/" + logoPath;
  }

  @SneakyThrows
  private void createBucket() {
    boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
        .bucket(minioProperties.getBucket())
        .build());
    if (!found) {
      minioClient.makeBucket(MakeBucketArgs.builder()
          .bucket(minioProperties.getBucket())
          .build());
    }
  }

  private String generateFileName(MultipartFile file) {
    String extension = getExtension(file);
    return UUID.randomUUID() + "." + extension;
  }

  private String getExtension(MultipartFile file) {
    return file.getOriginalFilename()
        .substring(file.getOriginalFilename().lastIndexOf(".") + 1);
  }

  @SneakyThrows
  private void saveImage(InputStream inputStream,
      String fileName) {
    minioClient.putObject(PutObjectArgs.builder()
        .stream(inputStream, inputStream.available(), -1)
        .bucket(minioProperties.getBucket())
        .object(fileName)
        .build());
  }

}
