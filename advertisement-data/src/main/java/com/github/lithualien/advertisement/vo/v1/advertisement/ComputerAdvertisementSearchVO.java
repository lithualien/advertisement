package com.github.lithualien.advertisement.vo.v1.advertisement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComputerAdvertisementSearchVO extends AdvertisementSearchVO {

    private String cpu;

    private String motherboard;

    private String gpu;

    private String ram;

    private String memory;

}
