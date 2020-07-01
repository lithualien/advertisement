package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.County;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;

public interface CountyRepository extends CrudRepository<County, Long> {

    Iterable<County> findByCounty(String county);
}
