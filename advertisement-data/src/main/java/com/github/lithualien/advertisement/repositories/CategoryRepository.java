package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {



}
