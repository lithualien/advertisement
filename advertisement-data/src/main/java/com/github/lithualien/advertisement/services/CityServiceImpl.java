package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.converters.CityConverter;
import com.github.lithualien.advertisement.converters.DozerConverter;
import com.github.lithualien.advertisement.exceptions.ResourceNotFoundException;
import com.github.lithualien.advertisement.models.City;
import com.github.lithualien.advertisement.models.County;
import com.github.lithualien.advertisement.repositories.CityRepository;
import com.github.lithualien.advertisement.repositories.CountyRepository;
import com.github.lithualien.advertisement.vo.v1.CityVO;
import com.github.lithualien.advertisement.vo.v1.CityWithCountyVO;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CountyRepository countyRepository;

    public CityServiceImpl(CityRepository cityRepository, CountyRepository countyRepository) {
        this.cityRepository = cityRepository;
        this.countyRepository = countyRepository;
    }

    @Override
    public Set<CityVO> findAll() {
        return DozerConverter.parseSet(StreamSupport
                .stream(cityRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet()), CityVO.class);
    }

    @Override
    public CityWithCountyVO save(CityWithCountyVO cityWithCounty) {
        City city = CityConverter.cityWithCityVOToCity(cityWithCounty, getCounty(cityWithCounty.getCounty()));
        city.setId(null);
        return CityConverter.cityToCityWithVO(cityRepository.save(city));
    }

    @Override
    public CityWithCountyVO update(CityWithCountyVO cityWithCounty) {
        City city = CityConverter.cityWithCityVOToCity(cityWithCounty, getCounty(cityWithCounty.getCounty()));
        return CityConverter.cityToCityWithVO(cityRepository.save(city));
    }

    @Override
    public void delete(Long id) {
        City city = cityRepository
                .findById(id)
                .<ResourceNotFoundException> orElseThrow( () -> {
                    throw new ResourceNotFoundException("City with id=" + id + " was not found.");
                });
        cityRepository.delete(city);
    }

    public County getCounty(String countyName) {
        return countyRepository
                .findByName(countyName)
                .<ResourceNotFoundException>orElseThrow(() -> {
                    throw new ResourceNotFoundException("Specified county was not found.");
                });
    }
}
