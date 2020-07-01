package com.github.lithualien.advertisement.vo.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityWithCountyVO extends CityVO {

    private String county;

    public CityWithCountyVO(Long id, String city, String county) {
        super(id, city);
        this.county = county;
    }

}
