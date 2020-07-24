package com.github.lithualien.advertisement.services.impl;

import com.github.lithualien.advertisement.converters.SubCategoryConverter;
import com.github.lithualien.advertisement.exceptions.ResourceAlreadyExistsException;
import com.github.lithualien.advertisement.exceptions.ResourceNotFoundException;
import com.github.lithualien.advertisement.models.Category;
import com.github.lithualien.advertisement.models.SubCategory;
import com.github.lithualien.advertisement.repositories.CategoryRepository;
import com.github.lithualien.advertisement.repositories.SubCategoryRepository;
import com.github.lithualien.advertisement.services.SubCategoryService;
import com.github.lithualien.advertisement.vo.v1.SubCategoryWithCategoryVO;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryServiceImpl(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public SubCategoryWithCategoryVO save(SubCategoryWithCategoryVO subCategoryWithCategoryVO) {

        Category category = getCategory(subCategoryWithCategoryVO);

        if(subCategoryRepository.findIfSubCategoryExists(subCategoryWithCategoryVO.getSubCategory(), category)) {
            throw new ResourceAlreadyExistsException("Specified sub category already exists.");
        }

        SubCategory subCategory = SubCategoryConverter.voToSubCategory(subCategoryWithCategoryVO, category);
        subCategory.setId(null);

        return SubCategoryConverter.subCategoryToVO(subCategoryRepository.save(subCategory));
    }

    @Override
    public SubCategoryWithCategoryVO update(SubCategoryWithCategoryVO subCategoryWithCategoryVO) {

        Category category = getCategory(subCategoryWithCategoryVO);

        if(subCategoryRepository.findIfSubCategoryExists(subCategoryWithCategoryVO.getSubCategory(), category)) {
            throw new ResourceAlreadyExistsException("Specified sub category already exists.");
        }

        SubCategory subCategory = SubCategoryConverter.voToSubCategory(subCategoryWithCategoryVO, category);

        return SubCategoryConverter.subCategoryToVO(subCategoryRepository.save(subCategory));
    }

    @Override
    public void delete(Long id) {
        SubCategory subCategory = getSubCategoryById(id);
        subCategoryRepository.delete(subCategory);
    }

    public Category getCategory(SubCategoryWithCategoryVO subCategoryWithCategoryVO) {
        return categoryRepository.findByCategory(subCategoryWithCategoryVO.getCategory())
                .<ResourceNotFoundException> orElseThrow( () -> {
                    throw new ResourceNotFoundException("Specified category does not exist.");
                });

    }

    public SubCategory getSubCategoryById(Long id) {
        return subCategoryRepository
                .findById(id)
                .<ResourceNotFoundException> orElseThrow( () -> {
                    throw new ResourceNotFoundException("Sub category with id=" + id + " was not found.");
                });
    }
}
