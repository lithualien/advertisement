package com.github.lithualien.advertisement.services;


import com.github.lithualien.advertisement.models.MonitorAdvertisement;
import com.github.lithualien.advertisement.vo.v1.advertisement.MonitorAdvertisementSearchVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.MonitorAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.MonitorAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface MonitorAdvertisementService {

    Page<MonitorAdvertisementWithImageVO> all(Pageable pageable);

    Page<MonitorAdvertisementWithImageVO> findBySubCategory(Pageable pageable, String subCategory);

    MonitorAdvertisementWithImageVO findById(Long id);

    MonitorAdvertisement findEntityById(Long id);

    Page<MonitorAdvertisementWithImageVO> findByUserId(Pageable pageable, Long id);

    Page<MonitorAdvertisementWithImageVO> findSearch(Pageable pageable,
                                                            MonitorAdvertisementSearchVO searchVO);

    MonitorAdvertisementWithImageVO save(MonitorAdvertisementVO advertisementVO, String username);

    MonitorAdvertisementWithImageVO update(MonitorAdvertisementVO advertisementVO, String username);

    void delete(Long id, String username, Authentication authentication);

    void isAdvertisementCreator(MonitorAdvertisement advertisement, String username);
}
