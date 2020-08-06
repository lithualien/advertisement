package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.ExternalDeviceAdvertisement;
import com.github.lithualien.advertisement.models.SubCategory;
import com.github.lithualien.advertisement.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalDeviceAdvertisementRepository extends AdvertisementRepository<ExternalDeviceAdvertisement> {

    Page<ExternalDeviceAdvertisement> findAllBySubCategory(Pageable pageable, SubCategory subCategory);

    Page<ExternalDeviceAdvertisement> findAllByUser(Pageable pageable, User user);

}
