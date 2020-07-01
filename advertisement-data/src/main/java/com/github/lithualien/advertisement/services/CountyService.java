package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.vo.v1.CountySetVO;
import com.github.lithualien.advertisement.vo.v1.CountyVO;

import java.util.Set;

public interface CountyService {

    Set<CountySetVO> all();

    Set<CountySetVO> findByCounty(String county);

    CountyVO update(CountyVO countyVO);
}
