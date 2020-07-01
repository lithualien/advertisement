package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.County;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CountyRepository extends CrudRepository<County, Long> {

    Iterable<County> findByCounty(String county);

    @Query("select county from County county where county.county = :county")
    Optional<County> findByName(String county);

    @Query("select case when count(county) > 0 then true else false end " +
            "from County county where county.county = :county")
    Boolean findIfExists(String county);
}
