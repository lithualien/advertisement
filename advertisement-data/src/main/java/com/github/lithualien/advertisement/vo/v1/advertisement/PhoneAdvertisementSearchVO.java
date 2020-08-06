package com.github.lithualien.advertisement.vo.v1.advertisement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneAdvertisementSearchVO extends AdvertisementSearchVO {

    private String manufacturer;

    private String model;

    private String os;

    private String camera;

    private String ram;

    private String memory;
}
