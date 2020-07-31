package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.aws.SpringCloudS3;
import com.github.lithualien.advertisement.exceptions.UnsupportedMediaType;
import com.github.lithualien.advertisement.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private final SpringCloudS3 springCloudS3;
    private final String HASH_ALGORITHM = "SHA-512";

    @Value("${cloud.aws.s3.bucket.image.url}")
    private String amazonUrl;

    public FileServiceImpl(SpringCloudS3 springCloudS3) {
        this.springCloudS3 = springCloudS3;
    }

    @Override
    public List<String> uploadFiles(List<MultipartFile> multipartFiles) {
        List<String> urls = new ArrayList<>();

        if(multipartFiles.isEmpty()) {
            return null;
        }

        multipartFiles
                .forEach(file -> {

                    if(!file.getContentType().contains("image")) {
                        throw new UnsupportedMediaType("Only images are allowed.");
                    }

                    String fileName = getHashedString(file.getOriginalFilename());
                    String url = amazonUrl + fileName;

                    springCloudS3.uploadFile(fileName, file);

                    urls.add(url);
                });
        return urls;
    }


    private String getHashedString(String fileName) {
        MessageDigest digest;
        fileName += System.currentTimeMillis();
        try {
            digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hash = digest.digest(fileName.getBytes(StandardCharsets.UTF_8));
            fileName = Base64.getEncoder().encodeToString(hash);
            fileName = fileName.replaceAll("\\W", "");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
