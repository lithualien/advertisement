package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.vo.v1.CityVO;
import com.github.lithualien.advertisement.vo.v1.CityWithCountyVO;

import java.util.Set;

public interface CityService {
    Set<CityVO> findAll();

    CityWithCountyVO save(CityWithCountyVO cityWithCounty);

    CityWithCountyVO update(CityWithCountyVO cityWithCounty);

    void delete(Long id);


}
