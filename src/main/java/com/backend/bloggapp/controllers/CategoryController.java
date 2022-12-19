package com.backend.bloggapp.controllers;

import com.backend.bloggapp.payloads.ApiResponse;
import com.backend.bloggapp.payloads.CategoryDto;
import com.backend.bloggapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(this.categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }
    // update
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Long categoryId) {
        return new ResponseEntity<>(this.categoryService.updateCategory(categoryDto, categoryId), HttpStatus.OK);
    }
    // delete
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category is deleted successfully", false), HttpStatus.OK);
    }
    // get one
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("categoryId") Long categoryId) {
        return new ResponseEntity<>(this.categoryService.getCategory(categoryId), HttpStatus.OK);
    }

    // get all
    @GetMapping("/getCategories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {

        return new ResponseEntity<>(this.categoryService.getAllCategories(), HttpStatus.OK);
    }
}
