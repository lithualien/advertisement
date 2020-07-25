package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.models.superclass.Advertisement;
import com.github.lithualien.advertisement.repositories.AdvertisementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class AbstractAdvertisementService<T extends Advertisement> {

    public Page<T> all(Pageable pageable, AdvertisementRepository<T> advertisementRepository) {
        return advertisementRepository
                .findAll(pageable);
    }
}
