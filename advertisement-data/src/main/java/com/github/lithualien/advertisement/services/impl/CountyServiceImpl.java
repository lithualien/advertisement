package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.converters.CountyConverter;
import com.github.lithualien.advertisement.converters.DozerConverter;
import com.github.lithualien.advertisement.exceptions.ResourceAlreadyExistsException;
import com.github.lithualien.advertisement.exceptions.ResourceNotFoundException;
import com.github.lithualien.advertisement.models.County;
import com.github.lithualien.advertisement.repositories.CountyRepository;
import com.github.lithualien.advertisement.services.CountyService;
import com.github.lithualien.advertisement.vo.v1.CountySetVO;
import com.github.lithualien.advertisement.vo.v1.CountyVO;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CountyServiceImpl implements CountyService {

    private final CountyRepository countyRepository;

    public CountyServiceImpl(CountyRepository countyRepository) {
        this.countyRepository = countyRepository;
    }

    @Override
    public Set<CountySetVO> all() {
        Set<County> counties = StreamSupport
                .stream(countyRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
        return DozerConverter.parseSet(counties, CountySetVO.class);
    }

    @Override
    public Set<CountySetVO> findByCounty(String county) {
        Set<County> counties = StreamSupport
                .stream(countyRepository.findByCounty(county).spliterator(), false)
                .collect(Collectors.toSet());

        if(counties.isEmpty()) {
            throw new ResourceNotFoundException("Specified county does not exist.");
        }

        return DozerConverter.parseSet(counties, CountySetVO.class);
    }

    @Override
    public CountyVO update(CountyVO countyVO) {
        if(countyRepository.findIfExists(countyVO.getCounty())) {
            throw new ResourceAlreadyExistsException("County already exists.");
        }
        County county = CountyConverter.countyVOToCounty(countyVO);
        return CountyConverter.countyToCountyVO(countyRepository.save(county));
    }
}
