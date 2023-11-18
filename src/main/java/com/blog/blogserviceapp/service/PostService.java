package com.blog.blogserviceapp.service;

import com.blog.blogserviceapp.model.Post;
import com.blog.blogserviceapp.payloads.request.PostRequest;
import com.blog.blogserviceapp.payloads.response.PostResponse;

import java.util.List;

public interface PostService {
    Post createPost(PostRequest postRequest, int userId, int categoryId);

    List<Post> getPostByCategory(int categoryId);

    List<Post> getPostByUser(int userId);

    PostResponse getAllPosts(int pageNumber, int pageSize,String sortBy,String sortDir);

    Post getPostByPostId(int postId);

    void deletePost(int postId);

    Post updatePost(PostRequest postRequest, int postId);

    List<Post> seachPost(String title);
}
