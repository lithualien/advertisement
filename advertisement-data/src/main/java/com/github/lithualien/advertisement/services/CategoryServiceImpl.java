package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.converters.DozerConverter;
import com.github.lithualien.advertisement.exceptions.ResourceNotFoundException;
import com.github.lithualien.advertisement.models.Category;
import com.github.lithualien.advertisement.repositories.CategoryRepository;
import com.github.lithualien.advertisement.vo.v1.CategoryVO;
import com.github.lithualien.advertisement.vo.v1.CategoryWithSubCategoriesVO;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<CategoryWithSubCategoriesVO> all() {
        return DozerConverter.parseSet(
                StreamSupport
                        .stream(categoryRepository.findAll().spliterator(), false)
                        .collect(Collectors.toSet()), CategoryWithSubCategoriesVO.class
        );
    }

    @Override
    public CategoryWithSubCategoriesVO findByCategory(String category) {
        return DozerConverter.parseObject(
                categoryRepository
                        .findByCategory(category)
                        .<ResourceNotFoundException> orElseThrow( () -> {
                            throw new ResourceNotFoundException("Category with given name was not found.");
                        }), CategoryWithSubCategoriesVO.class );
    }

    @Override
    public CategoryVO save(CategoryVO categoryVO) {
        Category category = DozerConverter.parseObject(categoryVO, Category.class);
        category.setId(null);
        categoryVO = DozerConverter.parseObject(categoryRepository.save(category), CategoryVO.class);
        return categoryVO;
    }

    @Override
    public CategoryVO update(CategoryVO categoryVO) {
        Category category = DozerConverter.parseObject(categoryVO, Category.class);
        categoryVO = DozerConverter.parseObject(categoryRepository.save(category), CategoryVO.class);
        return categoryVO;
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
