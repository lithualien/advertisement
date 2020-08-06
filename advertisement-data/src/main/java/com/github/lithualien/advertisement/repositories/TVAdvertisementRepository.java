package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.SubCategory;
import com.github.lithualien.advertisement.models.TVAdvertisement;
import com.github.lithualien.advertisement.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TVAdvertisementRepository extends AdvertisementRepository<TVAdvertisement> {

    Page<TVAdvertisement> findAllBySubCategory(Pageable pageable, SubCategory subCategory);

    Page<TVAdvertisement> findAllByUser(Pageable pageable, User user);

}
