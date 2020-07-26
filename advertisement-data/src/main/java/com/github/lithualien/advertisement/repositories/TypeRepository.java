package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TypeRepository extends CrudRepository<Type, Long> {

    Optional<Type> findByType(String type);

}
