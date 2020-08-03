package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComputerAdvertisementRepository extends AdvertisementRepository<ComputerAdvertisement> {

    Page<ComputerAdvertisement> findAllById(Pageable pageable, Long id);

}
