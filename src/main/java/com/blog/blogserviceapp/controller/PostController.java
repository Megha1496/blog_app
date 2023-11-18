package com.blog.blogserviceapp.controller;

import com.blog.blogserviceapp.model.Post;
import com.blog.blogserviceapp.payloads.request.PostRequest;
import com.blog.blogserviceapp.payloads.response.BaseResponse;
import com.blog.blogserviceapp.payloads.response.PostResponse;
import com.blog.blogserviceapp.service.PostService;
import com.blog.blogserviceapp.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<BaseResponse<?>> createPost(@Valid @RequestBody PostRequest postRequest,
                                                         @PathVariable int userId,
                                                         @PathVariable int categoryId){
       Post post = postService.createPost(postRequest,userId,categoryId);
        BaseResponse<Post> response = new BaseResponse<>("Post created successfully",
                true, post);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/posts/{categoryId}")
    public ResponseEntity<BaseResponse<?>> getPostByCategory(@PathVariable int categoryId){
        List<Post> post=postService.getPostByCategory(categoryId);
        return ResponseEntity.ok(new BaseResponse<>("Fetching all post by category id",true,post));
    }

    @GetMapping("/posts/{userId}")
    public ResponseEntity<BaseResponse<?>> getPostsByUser(@PathVariable int userId){
        List<Post> posts=postService.getPostByUser(userId);
        return ResponseEntity.ok(new BaseResponse<>("Fetching all post by user id",true,posts));
    }

    @GetMapping("/posts")
    public ResponseEntity<BaseResponse<?>> getAllPosts( @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) int pageNumber,
                                                        @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
                                                        @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
                                                        @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
        PostResponse posts= postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
        return ResponseEntity.ok(new BaseResponse<>("Fetching all posts",true,posts));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<BaseResponse<?>> getPostByPostId(@PathVariable int postId){
        Post posts=postService.getPostByPostId(postId);
        return ResponseEntity.ok(new BaseResponse<>("Fetching posts by id",true,posts));
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<BaseResponse<?>> deletePostById(@PathVariable int postId){
        postService.deletePost(postId);
        return ResponseEntity.ok(new BaseResponse<>("Post Delete Successfully",true,null));

    }

    @PutMapping("/post/{postId}")
    public ResponseEntity<BaseResponse<?>> updatePost(@RequestBody PostRequest postRequest, @PathVariable
                                                      int postId){
       Post post= postService.updatePost(postRequest,postId);
        return ResponseEntity.ok(new BaseResponse<>("Post Update Successfully",true,post));

    }

    @GetMapping("/search/{title}")
    public ResponseEntity<BaseResponse<?>> searchPost(@PathVariable String title){
       List<Post> post=postService.seachPost(title);
        return ResponseEntity.ok(new BaseResponse<>("List of Posts",true,post));


    }
}
