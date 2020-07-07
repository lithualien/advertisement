package com.github.lithualien.advertisement.controllers;

import com.github.lithualien.advertisement.services.CategoryService;
import com.github.lithualien.advertisement.vo.v1.CategoryVO;
import com.github.lithualien.advertisement.vo.v1.CategoryWithSubCategoriesVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Set;

@RestController
@RequestMapping("api/categories/v1")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Set<CategoryWithSubCategoriesVO> all() {
        return categoryService.all();
    }

    @GetMapping("/{category}")
    public CategoryWithSubCategoriesVO findByName(@PathVariable("category") String category) {
        return categoryService.findByCategory(category);
    }

    @PutMapping
    public CategoryVO update(@Valid @RequestBody CategoryVO categoryVO) {
        return categoryService.update(categoryVO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") @Min(1) Long id) {
        categoryService.delete(id);
    }
}
