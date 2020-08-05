package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.PhoneAdvertisement;
import com.github.lithualien.advertisement.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PhoneAdvertisementRepository extends AdvertisementRepository<PhoneAdvertisement> {

    Page<PhoneAdvertisement> findAllByUser(Pageable pageable, User user);

}
