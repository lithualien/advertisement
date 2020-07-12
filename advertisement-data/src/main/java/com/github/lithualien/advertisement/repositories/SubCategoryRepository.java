package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.Category;
import com.github.lithualien.advertisement.models.SubCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SubCategoryRepository extends CrudRepository<SubCategory, Long> {

    @Query("select case when count(subCategory) > 0 then true else false end "+
            "from SubCategory subCategory where subCategory.subCategory = :subCategory and subCategory.category = :category")
    Boolean findIfSubCategoryExists(String subCategory, Category category);

}
