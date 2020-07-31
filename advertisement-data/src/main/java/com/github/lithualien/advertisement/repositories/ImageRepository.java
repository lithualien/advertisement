package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.superclass.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository<T extends Image> extends CrudRepository<T, Long> {

}
