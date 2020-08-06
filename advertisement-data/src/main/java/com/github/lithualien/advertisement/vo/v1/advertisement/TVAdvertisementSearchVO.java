package com.github.lithualien.advertisement.vo.v1.advertisement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TVAdvertisementSearchVO extends AdvertisementSearchVO {

    private String brand;

    private String diameter;

    private String resolution;

    @JsonProperty("smart_tv")
    private String smartTV;

    @JsonProperty("3d")
    private String threeD;

}
