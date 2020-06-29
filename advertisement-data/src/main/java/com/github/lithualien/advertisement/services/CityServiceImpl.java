package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.converters.DozerConverter;
import com.github.lithualien.advertisement.models.City;
import com.github.lithualien.advertisement.models.County;
import com.github.lithualien.advertisement.repositories.CityRepository;
import com.github.lithualien.advertisement.vo.v1.CityVO;
import com.github.lithualien.advertisement.vo.v1.CountyVO;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Set<CityVO> findAll() {
        return DozerConverter.parseSet(StreamSupport
                .stream(cityRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet()), CityVO.class);
    }
}
