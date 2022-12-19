package com.backend.bloggapp.services.impl;

import com.backend.bloggapp.entities.Category;
import com.backend.bloggapp.exceptions.ResourceNotFoundException;
import com.backend.bloggapp.payloads.CategoryDto;
import com.backend.bloggapp.repositories.CategoryRepository;
import com.backend.bloggapp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImplementation implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category category = this.categoryRepository.save(cat);
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {
        Category oldCategory = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryID", categoryId));
        oldCategory.setCategoryTitle(categoryDto.getCategoryTitle());
        oldCategory.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory =  this.categoryRepository.save(oldCategory);
        return this.modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
//        Category cat = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryID", categoryId));
//        this.categoryRepository.delete(cat);

        this.categoryRepository.deleteById(categoryId);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category cat = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryID", categoryId));
        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return this.categoryRepository.findAll().stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }
}
