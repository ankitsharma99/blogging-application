package com.backend.bloggapp.services;

import com.backend.bloggapp.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    // create
    CategoryDto createCategory(CategoryDto categoryDto);
    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId);
    //delete
    void deleteCategory(Long categoryId);
    //get
    CategoryDto getCategory(Long categoryId);
    //get all
    List<CategoryDto> getAllCategories();
}
