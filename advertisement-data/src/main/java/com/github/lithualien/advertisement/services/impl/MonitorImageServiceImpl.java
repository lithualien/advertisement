package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.models.MonitorAdvertisement;
import com.github.lithualien.advertisement.models.MonitorImage;
import com.github.lithualien.advertisement.repositories.MonitorImageRepository;
import com.github.lithualien.advertisement.services.FileService;
import com.github.lithualien.advertisement.services.MonitorImageService;
import com.github.lithualien.advertisement.vo.v1.advertisement.MonitorAdvertisementWithImageVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MonitorImageServiceImpl extends AbstractImageService<MonitorImage> implements MonitorImageService {

    private final MonitorServiceImpl monitorService;

    public MonitorImageServiceImpl(MonitorServiceImpl monitorService, MonitorImageRepository monitorImageRepository,
                                   FileService fileService) {
        super(monitorImageRepository, fileService);
        this.monitorService = monitorService;
    }

    @Override
    public MonitorAdvertisementWithImageVO upload(List<MultipartFile> files, Long id, String username) {
        MonitorAdvertisement advertisement = monitorService.findEntityById(id);
        monitorService.isAdvertisementCreator(advertisement, username);
        List<String> urls = super.abstractUpload(files);
        saveImages(advertisement, urls);
        return monitorService.findById(id);
    }

    @Override
    public void delete(Long id, String username) {
        MonitorImage image = abstractGetImage(id);
        MonitorAdvertisement advertisement
                = monitorService.findEntityById(image.getMonitorAdvertisement().getId());
        monitorService.isAdvertisementCreator(advertisement, username);
        super.abstractDelete(image);
    }

    private void saveImages(MonitorAdvertisement advertisement, List<String> urls) {
        urls
                .forEach(url -> {
                    MonitorImage image = new MonitorImage();
                    image.setUrl(url);
                    image.setMonitorAdvertisement(advertisement);
                    super.abstractSave(image);
                });
    }

}
