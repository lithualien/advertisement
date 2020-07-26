package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.aws.SpringCloudS3;
import com.github.lithualien.advertisement.converters.ImageConverter;
import com.github.lithualien.advertisement.exceptions.NotContentCreatorException;
import com.github.lithualien.advertisement.exceptions.ResourceNotFoundException;
import com.github.lithualien.advertisement.exceptions.UnsupportedMediaType;
import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import com.github.lithualien.advertisement.models.ComputerImage;
import com.github.lithualien.advertisement.repositories.ComputerAdvertisementRepository;
import com.github.lithualien.advertisement.repositories.ImageRepository;
import com.github.lithualien.advertisement.services.FileService;
import com.github.lithualien.advertisement.vo.v1.ImageVO;
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
    private final ComputerAdvertisementRepository computerAdvertisementRepository;
    private final String HASH_ALGORITHM = "SHA-512";
    private final ImageRepository imageRepository;

    @Value("${cloud.aws.s3.bucket.image.url}")
    private String amazonUrl;

    public FileServiceImpl(SpringCloudS3 springCloudS3, ComputerAdvertisementRepository computerAdvertisementRepository, ImageRepository imageRepository) {
        this.springCloudS3 = springCloudS3;
        this.computerAdvertisementRepository = computerAdvertisementRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<ImageVO> uploadFiles(List<MultipartFile> multipartFiles, Long advertisement, String username) {
        List<ImageVO> urls = new ArrayList<>();
        if(multipartFiles.isEmpty()) {
            return null;
        }

        ComputerAdvertisement computerAdvertisement = getComputerAdvertisement(advertisement);

        if(!computerAdvertisement.getUser().getUsername().equals(username)) {
            throw new NotContentCreatorException("User did not create the advertisement.");
        }

        multipartFiles
                .forEach(file -> {
                    if(!file.getContentType().contains("image")) {
                        throw new UnsupportedMediaType("Only images are allowed.");
                    }
                    String fileName = getHashedString(file.getOriginalFilename());
                    String url = amazonUrl + fileName;
                    springCloudS3.uploadFile(fileName, file);
                    ImageVO image = ImageConverter.entityToImageVO(getImage(computerAdvertisement, url));
                    urls.add(image);
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

    private ComputerAdvertisement getComputerAdvertisement(Long id) {
        return computerAdvertisementRepository
                .findById(id)
                .<ResourceNotFoundException> orElseThrow( () -> {
                    throw new ResourceNotFoundException("Advertisement with id="+ id + " was not found.");
                });
    }

    private ComputerImage getImage(ComputerAdvertisement computerAdvertisement, String url) {
        ComputerImage image = new ComputerImage();
        image.setComputerAdvertisement(computerAdvertisement);
        image.setUrl(url);
        return imageRepository.save(image);
    }
}
