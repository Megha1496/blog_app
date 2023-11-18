package com.blog.blogserviceapp.service.impl;

import com.blog.blogserviceapp.exceptionHandling.ResourceNotFoundException;
import com.blog.blogserviceapp.model.Category;
import com.blog.blogserviceapp.model.User;
import com.blog.blogserviceapp.payloads.request.CategoryRequest;
import com.blog.blogserviceapp.repository.CategoryRepository;
import com.blog.blogserviceapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(CategoryRequest categoryRequest) {
        Category category=this.modelMapper.map(categoryRequest,Category.class);
        Category savedCategory= this.categoryRepository.save(category);
        return savedCategory;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> users= categoryRepository.findAll();
        return users;
    }

    @Override
    public Category findCategoryById(int id) {
        Category category= categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("category","id",id));
        return category;
    }

    @Override
    public Category updateCategory(int id, CategoryRequest categoryRequest) {
        Category category=  categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));
        category.setTitle(categoryRequest.getTitle());
        category.setDescription(categoryRequest.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public Category deleteCategory(int id) {
        Category category=  categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("category","id",id));
        categoryRepository.delete(category);
        return category;
    }
}
