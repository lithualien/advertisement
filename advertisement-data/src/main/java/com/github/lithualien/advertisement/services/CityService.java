package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.models.City;
import com.github.lithualien.advertisement.vo.v1.CityVO;

import java.util.Set;

public interface CityService {
    Set<CityVO> findAll();

    Set<City> findAllCity();

}
