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
public class ConsoleAdvertisementVO extends AdvertisementVO {

    @NotBlank(message = "model field is required")
    private String model;

    @NotBlank(message = "memory field is required")
    private String memory;

    @NotBlank(message = "color field is required")
    private String color;
}
