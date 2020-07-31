package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import com.github.lithualien.advertisement.models.ComputerImage;
import com.github.lithualien.advertisement.repositories.ComputerImageRepository;
import com.github.lithualien.advertisement.services.ComputerAdvertisementService;
import com.github.lithualien.advertisement.services.ComputerImageService;
import com.github.lithualien.advertisement.services.FileService;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementWithImageVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ComputerImageServiceImpl extends AbstractImageService<ComputerImage> implements ComputerImageService {

    private final ComputerAdvertisementService computerAdvertisementService;

    public ComputerImageServiceImpl(ComputerImageRepository computerImageComputerImageRepository,
                                    ComputerAdvertisementService computerAdvertisementService, FileService fileService) {
        super(computerImageComputerImageRepository, fileService);
        this.computerAdvertisementService = computerAdvertisementService;
    }

    @Override
    public ComputerAdvertisementWithImageVO upload(List<MultipartFile> files, Long id, String username) {
        ComputerAdvertisement computerAdvertisement = computerAdvertisementService.getAdvertisementById(id);
        computerAdvertisementService.isAdvertisementCreator(computerAdvertisement, username);
        List<String> urls = super.abstractUpload(files);
        saveImages(computerAdvertisement, urls);
        return computerAdvertisementService.findById(id);
    }

    @Override
    public void delete(Long id, String username) {
        ComputerImage image = super.abstractGetImage(id);
        ComputerAdvertisement computerAdvertisement =
                computerAdvertisementService.getAdvertisementById(image.getComputerAdvertisement().getId());
        computerAdvertisementService.isAdvertisementCreator(computerAdvertisement, username);
        super.abstractDelete(image);
    }

    private void saveImages(ComputerAdvertisement computerAdvertisement, List<String> urls) {
        urls
                .forEach(url -> {
                    ComputerImage image = new ComputerImage();
                    image.setUrl(url);
                    image.setComputerAdvertisement(computerAdvertisement);
                    super.abstractSave(image);
                });
    }
}
