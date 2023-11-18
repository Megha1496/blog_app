package com.blog.blogserviceapp.service;

import com.blog.blogserviceapp.model.Category;
import com.blog.blogserviceapp.model.User;
import com.blog.blogserviceapp.payloads.request.CategoryRequest;
import com.blog.blogserviceapp.payloads.request.UserRequest;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryRequest categoryRequest) ;

    List<Category> getAllCategories();

    Category findCategoryById(int id);

    Category updateCategory(int id, CategoryRequest categoryRequest);

    Category deleteCategory(int id);
}
