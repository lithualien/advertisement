package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.services.CityService;
import com.github.lithualien.advertisement.vo.v1.CityVO;
import com.github.lithualien.advertisement.vo.v1.CityWithCountyVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Set;

@RestController
@RequestMapping("api/cities/v1")
@Validated
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
    public CityWithCountyVO save(@Valid @RequestBody CityWithCountyVO cityWithCountyVO) {
        return cityService.save(cityWithCountyVO);
    }

    @PutMapping
    public CityWithCountyVO update(@Valid @RequestBody CityWithCountyVO cityWithCountyVO) {
        return cityService.update(cityWithCountyVO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @Min(1) Long id) {
        cityService.delete(id);
    }

}
