package com.github.lithualien.advertisement.vo.v1.advertisement;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComputerAdvertisementSearchVO implements Serializable {

    private String cpu;

    private String motherboard;

    private String gpu;

    private String ram;

    private String memory;

    private String city;

    @NotBlank(message = "sub_category is required")
    @JsonProperty(value = "sub_category")
    private String subCategory;

}
