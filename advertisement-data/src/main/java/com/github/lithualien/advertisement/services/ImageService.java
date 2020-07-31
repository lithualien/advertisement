package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.vo.v1.advertisement.AdvertisementVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService<T extends AdvertisementVO> {

    T upload(List<MultipartFile> files, Long id, String username);

    void delete(Long id, String username);

}
