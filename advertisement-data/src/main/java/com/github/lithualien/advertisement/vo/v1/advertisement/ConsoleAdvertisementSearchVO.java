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
public class ConsoleAdvertisementSearchVO {

    private String model;

    private String memory;

    private String color;

    private String city;

    @NotBlank(message = "sub_category field is requried")
    @JsonProperty("sub_category")
    private String subCategory;

}
