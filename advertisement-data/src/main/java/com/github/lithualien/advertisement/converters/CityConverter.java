package com.github.lithualien.advertisement.converters;

import com.github.lithualien.advertisement.models.City;
import com.github.lithualien.advertisement.models.County;
import com.github.lithualien.advertisement.vo.v1.CityWithCountyVO;

public class CityConverter {

    public static City cityWithCityVOToCity(CityWithCountyVO cityWithCountyVO, County county) {
        return new City(
                cityWithCountyVO.getId(),
                cityWithCountyVO.getCity(),
                county
                );
    }

    public static CityWithCountyVO cityToCityWithVO(City city) {
        return new CityWithCountyVO(
                city.getId(),
                city.getCity(),
                city.getCounty().getCounty()
        );
    }

}
