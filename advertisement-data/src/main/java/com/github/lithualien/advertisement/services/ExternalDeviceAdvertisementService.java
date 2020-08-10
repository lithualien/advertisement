package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.models.ExternalDeviceAdvertisement;
import com.github.lithualien.advertisement.vo.v1.advertisement.ExternalDeviceAdvertisementSearchVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ExternalDeviceAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ExternalDeviceAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExternalDeviceAdvertisementService {

    Page<ExternalDeviceAdvertisementWithImageVO> all(Pageable pageable, String subCategory);

    ExternalDeviceAdvertisementWithImageVO findById(Long id);

    ExternalDeviceAdvertisement findEntityById(Long id);

    Page<ExternalDeviceAdvertisementWithImageVO> findByUserId(Pageable pageable, Long id, String subCategory);

    Page<ExternalDeviceAdvertisementWithImageVO> findSearch(Pageable pageable,
                                                            ExternalDeviceAdvertisementSearchVO searchVO);

    ExternalDeviceAdvertisementWithImageVO save(ExternalDeviceAdvertisementVO advertisementVO, String username);

    ExternalDeviceAdvertisementWithImageVO update(ExternalDeviceAdvertisementVO advertisementVO, String username);

    void delete(Long id, String username);

    void isAdvertisement(ExternalDeviceAdvertisement advertisement, String username);
}
