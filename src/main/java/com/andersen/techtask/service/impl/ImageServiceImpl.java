package com.andersen.techtask.service.impl;

import com.andersen.techtask.exception.ImageUploadException;
import com.andersen.techtask.service.ImageService;
import com.andersen.techtask.service.props.MinioProperties;
import io.minio.*;

import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

//    @Override
//    public GetObjectResponse getLogoById(String logoId) throws Exception {
//        return minioClient.getObject(GetObjectArgs.builder()
//                .bucket(minioProperties.getBucket())
//                .object(logoId)
//                .build());
//    }
//
//    @Override
//    public String loadLogoFile(MultipartFile multipartFile) throws Exception {
//        var filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
//        minioClient.putObject(PutObjectArgs.builder()
//                .bucket(minioProperties.getBucket())
//                .object(filename)
//                .contentType(multipartFile.getContentType())
//                .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
//                .build());
//        return filename;
//    }
//
//    @Override
//    public void deleteLogoFile(String previousLogoUuid) throws Exception {
//        minioClient.removeObject(RemoveObjectArgs.builder()
//                .bucket(minioProperties.getBucket())
//                .object(previousLogoUuid)
//                .build());
//    }
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
    private void saveImage( InputStream inputStream,
                            String fileName) {
        minioClient.putObject(PutObjectArgs.builder()
                .stream(inputStream, inputStream.available(), -1)
                .bucket(minioProperties.getBucket())
                .object(fileName)
                .build());
    }
}


