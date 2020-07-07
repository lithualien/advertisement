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
public class SubCategoryVO {

    private Long id;

    @JsonProperty("sub_category")
    @NotBlank(message = "sub_category field is required")
    private String subCategory;

}
