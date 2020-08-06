package com.github.lithualien.advertisement.vo.v1.advertisement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExternalDeviceAdvertisementVO extends AdvertisementVO {

    @NotBlank(message = "brand field is required")
    private String brand;

    @NotBlank(message = "wireless field is required")
    private String wireless;

}
