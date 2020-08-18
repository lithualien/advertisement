package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.models.ConsoleAdvertisement;
import com.github.lithualien.advertisement.vo.v1.advertisement.ConsoleAdvertisementSearchVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ConsoleAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ConsoleAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface ConsoleAdvertisementService {

    Page<ConsoleAdvertisementWithImageVO> all(Pageable pageable);

    Page<ConsoleAdvertisementWithImageVO> findBySubCategory(Pageable pageable, String subCategory);

    ConsoleAdvertisementWithImageVO findById(Long id);

    ConsoleAdvertisement findEntityById(Long id);

    Page<ConsoleAdvertisementWithImageVO> findByUserId(Pageable pageable, Long id);

    Page<ConsoleAdvertisementWithImageVO> findSearch(Pageable pageable, ConsoleAdvertisementSearchVO searchVO);

    ConsoleAdvertisementWithImageVO save(ConsoleAdvertisementVO consoleAdvertisementVO, String username);

    ConsoleAdvertisementWithImageVO update(ConsoleAdvertisementVO consoleAdvertisementVO, String username);

    void delete(Long id, String username, Authentication authentication);

    void isAdvertisementCreator(ConsoleAdvertisement consoleAdvertisement, String username);
}
