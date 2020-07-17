package com.github.lithualien.advertisement.converters;

import com.github.lithualien.advertisement.models.Category;
import com.github.lithualien.advertisement.models.SubCategory;
import com.github.lithualien.advertisement.vo.v1.SubCategoryWithCategoryVO;

public class SubCategoryConverter {

    public static SubCategory voToSubCategory(SubCategoryWithCategoryVO subCategoryWithCategoryVO, Category category) {
        SubCategory subCategory = DozerConverter.parseObject(subCategoryWithCategoryVO, SubCategory.class);
        subCategory.setCategory(category);
        return subCategory;
    }

    public static SubCategoryWithCategoryVO subCategoryToVO(SubCategory subCategory) {
        SubCategoryWithCategoryVO subCategoryWithCategoryVO = DozerConverter.parseObject(subCategory, SubCategoryWithCategoryVO.class);
        subCategoryWithCategoryVO.setCategory(subCategory.getCategory().getCategory());
        return subCategoryWithCategoryVO;
    }

}
