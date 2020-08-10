package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.services.MonitorImageService;
import com.github.lithualien.advertisement.vo.v1.advertisement.MonitorAdvertisementWithImageVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MonitorImageServiceImpl implements MonitorImageService {
    @Override
    public MonitorAdvertisementWithImageVO upload(List<MultipartFile> files, Long id, String username) {
        return null;
    }

    @Override
    public void delete(Long id, String username) {

    }


}
