package com.github.lithualien.advertisement.vo.v1.advertisement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AdvertisementSearchVO implements Serializable {

    protected String city;

    @NotBlank(message = "sub_category field is requried")
    @JsonProperty("sub_category")
    protected String subCategory;

}
