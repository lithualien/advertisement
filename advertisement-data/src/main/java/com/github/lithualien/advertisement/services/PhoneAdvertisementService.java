package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.models.PhoneAdvertisement;
import com.github.lithualien.advertisement.vo.v1.advertisement.PhoneAdvertisementSearchVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.PhoneAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.PhoneAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface PhoneAdvertisementService {

    Page<PhoneAdvertisementWithImageVO> all(Pageable pageable);

    Page<PhoneAdvertisementWithImageVO> findBySubCategory(Pageable pageable, String subCategory);

    PhoneAdvertisementWithImageVO findById(Long id);

    PhoneAdvertisement findEntityById(Long id);

    Page<PhoneAdvertisementWithImageVO> findByUserId(Pageable pageable, Long id);

    Page<PhoneAdvertisementWithImageVO> findSearch(Pageable pageable, PhoneAdvertisementSearchVO searchVO);

    PhoneAdvertisementWithImageVO save(PhoneAdvertisementVO phoneAdvertisementVO, String username);

    PhoneAdvertisementWithImageVO update(PhoneAdvertisementVO phoneAdvertisementVO, String username);

    void delete(Long id, String username, Authentication authentication);

    void isAdvertisementCreator(PhoneAdvertisement phoneAdvertisement, String username);
}
