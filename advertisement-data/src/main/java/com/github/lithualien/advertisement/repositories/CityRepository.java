package com.github.lithualien.advertisement.repositories;

import com.github.lithualien.advertisement.models.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {

    @Query("select city from City city where city.city = :city")
    City findByCity(String city);

    @Query("select city from City city where city = :city")
    City getCity(City city);

}
