package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementSearchVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface ComputerAdvertisementService {

    Page<ComputerAdvertisementWithImageVO> all(Pageable pageable);

    Page<ComputerAdvertisementWithImageVO> findBySubCategory(Pageable pageable, String subCategory);

    ComputerAdvertisementWithImageVO save(ComputerAdvertisementVO computerAdvertisementVO,
                                                  String username);

    ComputerAdvertisementWithImageVO update(ComputerAdvertisementVO computerAdvertisementVO, String username);

    void delete (Long id, String username, Authentication authentication);

    ComputerAdvertisementWithImageVO findById(Long id);

    Page<ComputerAdvertisementWithImageVO> findByUserId(Pageable pageable, Long id);

    void isAdvertisementCreator(ComputerAdvertisement computerAdvertisement, String username);

    ComputerAdvertisement getAdvertisementById(Long id);

    Page<ComputerAdvertisementWithImageVO> findAllBaseOnSearch(Pageable pageable,
                                                               ComputerAdvertisementSearchVO searchVO);
}
