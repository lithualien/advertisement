package com.github.lithualien.advertisement.vo.v1.advertisement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.lithualien.advertisement.vo.v1.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dozer.Mapping;

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

    @Mapping("this")
    @JsonProperty("sub_category")
    @NotBlank(message = "sub_category field is required")
    protected String subCategory;

    @Mapping("this")
    @NotBlank(message = "type field is required")
    protected String type;

    @Mapping("this")
    @NotBlank(message = "city field is required")
    protected String city;

    protected Double price;
}
