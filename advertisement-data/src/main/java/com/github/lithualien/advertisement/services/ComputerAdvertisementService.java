package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import com.github.lithualien.advertisement.vo.v1.ComputerAdvertisementVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ComputerAdvertisementService {

    Page<ComputerAdvertisement> all(Pageable pageable);

    ComputerAdvertisementVO findAdvertisement(String category);

    ComputerAdvertisementVO save(ComputerAdvertisementVO computerAdvertisementVO);

    ComputerAdvertisementVO update(ComputerAdvertisementVO computerAdvertisementVO);

    void delete (Long id);
}
