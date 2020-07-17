package com.github.lithualien.advertisement.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryWithSubCategoriesVO extends CategoryVO {

    @JsonProperty("sub_categories")
    private Set<SubCategoryVO> subCategories = new HashSet<>();

}
