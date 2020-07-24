package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.services.SubCategoryService;
import com.github.lithualien.advertisement.vo.v1.SubCategoryWithCategoryVO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("api/sub-categories/v1")
@Validated
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("/{id}")
    public SubCategoryWithCategoryVO findById(@PathVariable("id") Long id) {
        return subCategoryService.findById(id);
    }

    @PostMapping
    public SubCategoryWithCategoryVO save(@Valid @RequestBody SubCategoryWithCategoryVO subCategoryWithCategoryVO) {
        return subCategoryService.save(subCategoryWithCategoryVO);
    }

    @PutMapping
    public SubCategoryWithCategoryVO update(@Valid @RequestBody SubCategoryWithCategoryVO subCategoryWithCategoryVO) {
        return subCategoryService.update(subCategoryWithCategoryVO);
    }

    @DeleteMapping( {"/{id}"} )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable("id") @Min(1) Long id) {
        subCategoryService.delete(id);
    }
}
