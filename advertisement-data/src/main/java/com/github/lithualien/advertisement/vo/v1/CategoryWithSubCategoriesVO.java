package com.github.lithualien.advertisement.vo.v1;

import com.github.lithualien.advertisement.models.Category;
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
public class CategoryWithSubCategoriesVO extends Category {

    private Set<SubCategoryVO> subCategories = new HashSet<>();

}
