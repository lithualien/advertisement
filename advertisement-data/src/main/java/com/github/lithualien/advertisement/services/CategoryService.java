package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.vo.v1.CategoryVO;
import com.github.lithualien.advertisement.vo.v1.CategoryWithSubCategoriesVO;

import java.util.Set;

public interface CategoryService {

    Set<CategoryWithSubCategoriesVO> all();

    CategoryWithSubCategoriesVO findByCategory(String category);

    CategoryVO save(CategoryVO categoryVO);

    CategoryVO update(CategoryVO categoryVO);

    void delete(Long id);

}
