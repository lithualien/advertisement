package com.github.lithualien.advertisement.vo.v1.advertisement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsoleAdvertisementSearchVO extends AdvertisementSearchVO {

    private String model;

    private String memory;

    private String color;
}
