package com.github.lithualien.advertisement.vo.v1;

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
public abstract class AdvertisementVO extends BaseVO {
    @NotBlank(message = "article field is required")
    protected String article;

    @NotBlank(message = "description field is required")
    protected String description;

    @JsonProperty("sub_category")
    @NotBlank(message = "sub_category field is required")
    protected String subCategory;

    @NotBlank(message = "type field is required")
    protected String type;
}
