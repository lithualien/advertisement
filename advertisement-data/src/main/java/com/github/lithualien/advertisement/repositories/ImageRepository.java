package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.superclass.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {

//    @Query("SELECT image FROM Image image WHERE image.computerAdvertisement = :advertisement AND image.subCategory = :subCategory")
//    Iterable<Image> findAllByAdvertisementAndSubCategory(Advertisement advertisement, SubCategory subCategory);

}
