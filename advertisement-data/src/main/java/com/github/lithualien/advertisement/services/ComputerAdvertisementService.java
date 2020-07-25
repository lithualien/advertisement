package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.vo.v1.ComputerAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.ComputerAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComputerAdvertisementService {

    Page<ComputerAdvertisementWithImageVO> all(Pageable pageable);

    ComputerAdvertisementVO save(ComputerAdvertisementVO computerAdvertisementVO);

    ComputerAdvertisementVO update(ComputerAdvertisementVO computerAdvertisementVO);

    void delete (Long id);
}
