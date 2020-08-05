package com.github.lithualien.advertisement.vo.v1.advertisement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneAdvertisementSearchVO implements Serializable {

    private String manufacturer;

    private String model;

    private String os;

    private String camera;

    private String ram;

    private String memory;

    private String city;

    @NotBlank(message = "sub_category field is required")
    @JsonProperty(value = "sub_category")
    private String subCategory;
}
