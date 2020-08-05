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
public class PhoneAdvertisementVO extends AdvertisementVO {

    @NotBlank(message = "manufacturer field is required")
    private String manufacturer;

    @NotBlank(message = "model field is required")
    private String model;

    @NotBlank(message = "os field is required")
    private String os;

    @NotBlank(message = "camera field is required")
    private String camera;

    @NotBlank(message = "ram field is required")
    private String ram;

    @NotBlank(message = "memory field is required")
    private String memory;
}
