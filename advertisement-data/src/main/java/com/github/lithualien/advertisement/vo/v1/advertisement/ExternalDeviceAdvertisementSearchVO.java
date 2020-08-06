package com.github.lithualien.advertisement.vo.v1.advertisement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExternalDeviceAdvertisementSearchVO extends AdvertisementSearchVO {

    private String brand;

    private String wireless;

}
