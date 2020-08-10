package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.ConsoleAdvertisement;
import com.github.lithualien.advertisement.models.SubCategory;
import com.github.lithualien.advertisement.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsoleAdvertisementRepository extends AdvertisementRepository<ConsoleAdvertisement> {

    Page<ConsoleAdvertisement> findAllByUser(Pageable pageable, User user);

    Page<ConsoleAdvertisement> findAllBySubCategory(Pageable pageable, SubCategory subCategory);

}
