package com.github.lithualien.advertisement.services;

import com.github.lithualien.advertisement.vo.v1.SubCategoryWithCategoryVO;

public interface SubCategoryService {

    SubCategoryWithCategoryVO save(SubCategoryWithCategoryVO subCategoryVO);

    SubCategoryWithCategoryVO update(SubCategoryWithCategoryVO subCategoryVO);

    void delete(Long id);

}
