package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.services.ExternalDeviceImageService;
import com.github.lithualien.advertisement.vo.v1.advertisement.ExternalDeviceAdvertisementWithImageVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ExternalDeviceImageServiceImpl implements ExternalDeviceImageService {
    @Override
    public ExternalDeviceAdvertisementWithImageVO upload(List<MultipartFile> files, Long id, String username) {
        return null;
    }

    @Override
    public void delete(Long id, String username) {

    }
}
