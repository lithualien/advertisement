package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.vo.v1.CategoryVO;
import com.github.lithualien.advertisement.vo.v1.CategoryWithSubCategoriesVO;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public Set<CategoryWithSubCategoriesVO> all() {
        return null;
    }

    @Override
    public CategoryWithSubCategoriesVO findByCategory(String category) {
        return null;
    }

    @Override
    public CategoryVO save(CategoryVO categoryVO) {
        return null;
    }

    @Override
    public CategoryVO update(CategoryVO categoryVO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
