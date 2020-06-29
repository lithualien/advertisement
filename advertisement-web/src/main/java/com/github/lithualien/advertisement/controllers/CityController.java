package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.models.City;
import com.github.lithualien.advertisement.services.CityService;
import com.github.lithualien.advertisement.vo.v1.CityVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/cities/v1")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/city/vo")
    public Set<CityVO> getCitiesVO() {
        return cityService.findAll();
    }

    @GetMapping("/city")
    public Set<City> getCities() {
        return cityService.findAllCity();
    }

}
