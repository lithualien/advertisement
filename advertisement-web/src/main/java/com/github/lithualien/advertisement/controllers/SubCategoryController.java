package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.services.SubCategoryService;
import com.github.lithualien.advertisement.vo.v1.SubCategoryVO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("api/sub-categories/v1")
@Validated
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @PostMapping
    public SubCategoryVO save(@Valid @RequestBody SubCategoryVO subCategoryVO) {
        return subCategoryService.save(subCategoryVO);
    }

    @PutMapping
    public SubCategoryVO update(@Valid @RequestBody SubCategoryVO subCategoryVO) {
        return subCategoryService.update(subCategoryVO);
    }

    @DeleteMapping( {"/{id}"} )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable("id") @Min(1) Long id) {
        subCategoryService.delete(id);
    }
}
