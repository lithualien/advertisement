package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("select category from Category category where category.category = :category")
    Optional<Category> findByCategory(String category);

}
