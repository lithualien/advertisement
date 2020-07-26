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
public class ComputerAdvertisementVO extends AdvertisementVO {

    @NotBlank(message = "cpu field is required")
    private String cpu;

    @NotBlank(message = "motherboard field is required")
    private String motherboard;

    @NotBlank(message = "gpu field is required")
    private String gpu;

    @NotBlank(message = "ram field is required")
    private String ram;

    @NotBlank(message = "memory field is required")
    private String memory;



}
