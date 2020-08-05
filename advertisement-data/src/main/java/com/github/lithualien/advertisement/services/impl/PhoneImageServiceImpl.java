package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.models.PhoneAdvertisement;
import com.github.lithualien.advertisement.models.PhoneImage;
import com.github.lithualien.advertisement.repositories.PhoneImageRepository;
import com.github.lithualien.advertisement.services.FileService;
import com.github.lithualien.advertisement.services.PhoneAdvertisementService;
import com.github.lithualien.advertisement.services.PhoneImageService;
import com.github.lithualien.advertisement.vo.v1.advertisement.PhoneAdvertisementWithImageVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PhoneImageServiceImpl extends AbstractImageService<PhoneImage> implements PhoneImageService {

    private final PhoneAdvertisementService phoneAdvertisementService;

    public PhoneImageServiceImpl(PhoneImageRepository phoneImageRepository, FileService fileService,
                                 PhoneAdvertisementService phoneAdvertisementService) {
        super(phoneImageRepository, fileService);
        this.phoneAdvertisementService = phoneAdvertisementService;
    }

    @Override
    public PhoneAdvertisementWithImageVO upload(List<MultipartFile> files, Long id, String username) {
        PhoneAdvertisement phoneAdvertisement = phoneAdvertisementService.findEntityById(id);
        phoneAdvertisementService.isAdvertisementCreator(phoneAdvertisement, username);
        List<String> urls = super.abstractUpload(files);
        saveImages(phoneAdvertisement, urls);
        return phoneAdvertisementService.findById(id);
    }

    @Override
    public void delete(Long id, String username) {
        PhoneImage image = abstractGetImage(id);
        PhoneAdvertisement phoneAdvertisement =
                phoneAdvertisementService.findEntityById(id);
        phoneAdvertisementService.isAdvertisementCreator(phoneAdvertisement, username);
        super.abstractDelete(image);
    }

    private void saveImages(PhoneAdvertisement phoneAdvertisement, List<String> urls) {
        urls
                .forEach(url -> {
                    PhoneImage image = new PhoneImage();
                    image.setUrl(url);
                    image.setPhoneAdvertisement(phoneAdvertisement);
                    super.abstractSave(image);
                });
    }
}
