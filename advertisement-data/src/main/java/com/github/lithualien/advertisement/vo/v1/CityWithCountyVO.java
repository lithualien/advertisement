package com.github.lithualien.advertisement.vo.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityWithCountyVO extends CityVO {

    @NotBlank(message = "county field is required")
    private String county;

    public CityWithCountyVO(Long id, String city, String county) {
        super(id, city);
        this.county = county;
    }

}
