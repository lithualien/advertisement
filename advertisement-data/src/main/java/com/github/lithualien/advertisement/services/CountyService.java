package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.vo.v1.CountyVO;

import java.util.Set;

public interface CountyService {

    Set<CountyVO> all();

    Set<CountyVO> findByCounty(String county);
}
