package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.services.CityService;
import com.github.lithualien.advertisement.vo.v1.CityVO;
import com.github.lithualien.advertisement.vo.v1.CityWithCountyVO;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/cities/v1")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public Set<CityVO> getCitiesVO() {
        return cityService.findAll();
    }

    @PostMapping
    public CityWithCountyVO save(@RequestBody CityWithCountyVO cityWithCountyVO) {
        return cityService.save(cityWithCountyVO);
    }

    @PutMapping
    public CityWithCountyVO update(@RequestBody CityWithCountyVO cityWithCountyVO) {
        return cityService.update(cityWithCountyVO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        cityService.delete(id);
    }

}
