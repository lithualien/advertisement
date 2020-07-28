package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementVO;
import com.github.lithualien.advertisement.vo.v1.advertisement.ComputerAdvertisementWithImageVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComputerAdvertisementService {

    Page<ComputerAdvertisementWithImageVO> all(Pageable pageable);

    ComputerAdvertisementWithImageVO save(ComputerAdvertisementVO computerAdvertisementVO,
                                                  String username);

    ComputerAdvertisementWithImageVO update(ComputerAdvertisementVO computerAdvertisementVO, String username);

    void delete (Long id);
}
