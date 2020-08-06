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
public class MonitorAdvertisementVO extends AdvertisementVO {

    @NotBlank(message = "brand fiend is required")
    private String brand;

    @NotBlank(message = "model fiend is required")
    private String model;

    @NotBlank(message = "resolution fiend is required")
    private String resolution;

    @NotBlank(message = "refresh_rate fiend is required")
    @JsonProperty("refresh_rate")
    private String refreshRate;

    @NotBlank(message = "response_time fiend is required")
    @JsonProperty("response_time")
    private String responseTime;

}
