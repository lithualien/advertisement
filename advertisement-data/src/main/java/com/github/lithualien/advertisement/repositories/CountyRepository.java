package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.County;
import org.springframework.data.repository.CrudRepository;

public interface CountyRepository extends CrudRepository<County, Long> {

    County findByCounty(String county);

}
