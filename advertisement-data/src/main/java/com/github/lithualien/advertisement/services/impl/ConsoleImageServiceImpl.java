package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.models.ConsoleAdvertisement;
import com.github.lithualien.advertisement.models.ConsoleImage;
import com.github.lithualien.advertisement.repositories.ConsoleImageRepository;
import com.github.lithualien.advertisement.services.ConsoleAdvertisementService;
import com.github.lithualien.advertisement.services.ConsoleImageService;
import com.github.lithualien.advertisement.services.FileService;
import com.github.lithualien.advertisement.vo.v1.advertisement.ConsoleAdvertisementWithImageVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ConsoleImageServiceImpl extends AbstractImageService<ConsoleImage> implements ConsoleImageService {

    private final ConsoleAdvertisementService consoleAdvertisementService;

    public ConsoleImageServiceImpl(ConsoleImageRepository consoleImageRepository,
                                   ConsoleAdvertisementService consoleAdvertisementService, FileService fileService) {
        super(consoleImageRepository, fileService);
        this.consoleAdvertisementService = consoleAdvertisementService;
    }

    @Override
    public ConsoleAdvertisementWithImageVO upload(List<MultipartFile> files, Long id, String username) {
        ConsoleAdvertisement advertisement = consoleAdvertisementService.findEntityById(id);
        consoleAdvertisementService.isAdvertisementCreator(advertisement, username);
        List<String> urls = super.abstractUpload(files);
        saveImages(advertisement, urls);
        return consoleAdvertisementService.findById(id);
    }

    @Override
    public void delete(Long id, String username) {
        ConsoleImage image = super.abstractGetImage(id);
        ConsoleAdvertisement advertisement
                = consoleAdvertisementService.findEntityById(image.getConsoleAdvertisement().getId());
        consoleAdvertisementService.isAdvertisementCreator(advertisement, username);
        super.abstractDelete(image);
    }

    private void saveImages(ConsoleAdvertisement advertisement, List<String> urls) {
        urls
                .forEach(url -> {
                    ConsoleImage image = new ConsoleImage();
                    image.setUrl(url);
                    image.setConsoleAdvertisement(advertisement);
                    super.abstractSave(image);
                });
    }
}
