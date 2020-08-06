package com.github.lithualien.advertisement.vo.v1.advertisement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonitorAdvertisementSearchVO extends AdvertisementSearchVO {

    private String brand;

    private String model;

    private String resolution;

    @JsonProperty("refresh_rate")
    private String refreshRate;

    @JsonProperty("response_time")
    private String responseTime;

}
