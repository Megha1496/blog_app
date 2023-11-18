package com.blog.blogserviceapp.controller;

import com.blog.blogserviceapp.model.Category;
import com.blog.blogserviceapp.model.User;
import com.blog.blogserviceapp.payloads.request.CategoryRequest;
import com.blog.blogserviceapp.payloads.request.UserRequest;
import com.blog.blogserviceapp.payloads.response.BaseResponse;
import com.blog.blogserviceapp.service.CategoryService;
import com.blog.blogserviceapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<BaseResponse<Category>> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        Category category= categoryService.createCategory(categoryRequest);
        BaseResponse<Category> response = new BaseResponse<>("Category created successfully", true, category);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/categories")
    public ResponseEntity<?>getAllCategories(){
        List<Category> categories=categoryService.getAllCategories();
        return ResponseEntity.ok(new BaseResponse<>("List of all categories",true,categories));

    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?>getCategoryById(@PathVariable int id){
        Category category=categoryService.findCategoryById(id);
        return ResponseEntity.ok(new BaseResponse<>("Fetching Category Successfully",true,category));
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<?>updateCategory(@PathVariable int id, @RequestBody @Valid CategoryRequest categoryRequest){
        Category category= categoryService.updateCategory(id,categoryRequest);
        return ResponseEntity.ok(new BaseResponse<>("Category Update Successfully",true,category));
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id){
        Category category= categoryService.deleteCategory(id);
        return ResponseEntity.ok(new BaseResponse<>("Category Delete Successfully",true,category));
    }

}
