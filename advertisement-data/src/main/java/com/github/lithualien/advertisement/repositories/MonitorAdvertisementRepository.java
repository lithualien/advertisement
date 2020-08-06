package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.MonitorAdvertisement;
import com.github.lithualien.advertisement.models.SubCategory;
import com.github.lithualien.advertisement.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MonitorAdvertisementRepository extends AdvertisementRepository<MonitorAdvertisement> {

    Page<MonitorAdvertisement> findAllBySubCategory(Pageable pageable, SubCategory subCategory);

    Page<MonitorAdvertisement> findAllByUser(Pageable pageable, User user);
}
