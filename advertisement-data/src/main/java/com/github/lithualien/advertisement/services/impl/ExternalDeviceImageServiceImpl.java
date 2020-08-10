package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.models.ExternalDeviceAdvertisement;
import com.github.lithualien.advertisement.models.ExternalDeviceImage;
import com.github.lithualien.advertisement.repositories.ExternalDeviceImageRepository;
import com.github.lithualien.advertisement.services.ExternalDeviceAdvertisementService;
import com.github.lithualien.advertisement.services.ExternalDeviceImageService;
import com.github.lithualien.advertisement.services.FileService;
import com.github.lithualien.advertisement.vo.v1.advertisement.ExternalDeviceAdvertisementWithImageVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ExternalDeviceImageServiceImpl extends AbstractImageService<ExternalDeviceImage> implements ExternalDeviceImageService {

    private final ExternalDeviceAdvertisementService externalDeviceAdvertisementService;

    public ExternalDeviceImageServiceImpl(ExternalDeviceImageRepository externalDeviceImageRepository,
                                          ExternalDeviceAdvertisementService externalDeviceAdvertisementService,
                                          FileService fileService) {

        super(externalDeviceImageRepository, fileService);
        this.externalDeviceAdvertisementService = externalDeviceAdvertisementService;
    }

    @Override
    public ExternalDeviceAdvertisementWithImageVO upload(List<MultipartFile> files, Long id, String username) {
        ExternalDeviceAdvertisement advertisement = externalDeviceAdvertisementService.findEntityById(id);
        externalDeviceAdvertisementService.isAdvertisement(advertisement, username);
        List<String> urls = super.abstractUpload(files);
        saveImages(advertisement, urls);
        return externalDeviceAdvertisementService.findById(id);
    }

    @Override
    public void delete(Long id, String username) {
        ExternalDeviceImage image = super.abstractGetImage(id);
        ExternalDeviceAdvertisement advertisement
                = externalDeviceAdvertisementService.findEntityById(image.getExternalDeviceAdvertisement().getId());
        externalDeviceAdvertisementService.isAdvertisement(advertisement, username);
        super.abstractDelete(image);
    }

    private void saveImages(ExternalDeviceAdvertisement advertisement, List<String> urls) {
        urls
                .forEach(url -> {
                    ExternalDeviceImage image = new ExternalDeviceImage();
                    image.setUrl(url);
                    image.setExternalDeviceAdvertisement(advertisement);
                    super.abstractSave(image);
                });
    }
}
