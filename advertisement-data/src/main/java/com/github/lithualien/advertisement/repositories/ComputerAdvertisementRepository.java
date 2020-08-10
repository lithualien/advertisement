package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.ComputerAdvertisement;
import com.github.lithualien.advertisement.models.SubCategory;
import com.github.lithualien.advertisement.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComputerAdvertisementRepository extends AdvertisementRepository<ComputerAdvertisement> {

    Page<ComputerAdvertisement> findAllByUser(Pageable pageable, User user);

    Page<ComputerAdvertisement> findAllBySubCategory(Pageable pageable, SubCategory subCategory);
}
