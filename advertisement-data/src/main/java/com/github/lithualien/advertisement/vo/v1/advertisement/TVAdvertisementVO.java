package com.github.lithualien.advertisement.vo.v1.advertisement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TVAdvertisementVO extends AdvertisementVO {

    @NotBlank(message = "brand field is required")
    private String brand;

    @NotBlank(message = "brand field is required")
    private String diameter;

    @NotBlank(message = "brand field is required")
    private String resolution;

    @NotBlank(message = "brand field is required")
    @JsonProperty("smart_tv")
    private String smartTV;

    @NotBlank(message = "brand field is required")
    @JsonProperty("3d")
    private String threeD;

}
