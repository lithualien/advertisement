package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.services.CountyService;
import com.github.lithualien.advertisement.vo.v1.CountySetVO;
import com.github.lithualien.advertisement.vo.v1.CountyVO;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/counties/v1")
public class CountyController {

    private final CountyService countyService;

    public CountyController(CountyService countyService) {
        this.countyService = countyService;
    }

    @GetMapping
    public Set<CountySetVO> all() {
        return countyService.all();
    }

    @GetMapping("/county")
    public Set<CountySetVO> byCounty(@RequestParam String county) {
        return countyService.findByCounty(county);
    }

    @PutMapping
    public CountyVO update(@RequestBody CountyVO countyVO) {
        return countyService.update(countyVO);
    }

}
