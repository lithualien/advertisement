package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.City;
import com.github.lithualien.advertisement.models.County;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    @Query("select city from City city where city.city = :city and city.county = :county")
    Optional<City> findByCityAndCounty(String city, County county);

    @Query("select case when count (city) > 0 then true else false end " +
            "from City city where city.city = :city and city.county = :county")
    Boolean findIfCityExists(String city, County county);

}
