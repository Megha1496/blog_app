package com.blog.blogserviceapp.service.impl;

import com.blog.blogserviceapp.exceptionHandling.ResourceNotFoundException;
import com.blog.blogserviceapp.model.Category;
import com.blog.blogserviceapp.model.Post;
import com.blog.blogserviceapp.model.User;
import com.blog.blogserviceapp.payloads.request.PostRequest;
import com.blog.blogserviceapp.payloads.response.PostResponse;
import com.blog.blogserviceapp.repository.CategoryRepository;
import com.blog.blogserviceapp.repository.PostRepository;
import com.blog.blogserviceapp.repository.UserRepository;
import com.blog.blogserviceapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    public PostServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Post createPost(PostRequest postRequest,int userId, int categoryId) {

        User user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
        Post post=modelMapper.map(postRequest,Post.class);
        post.setUser(user);
        post.setCategory(category);
        post.setDate(LocalDateTime.now());
        return postRepository.save(post);
    }

    @Override
    public List<Post> getPostByCategory(int categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
        List<Post> post=postRepository.findByCategory(category);
        return post;
    }

    @Override
    public List<Post> getPostByUser(int userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
        List<Post>posts=postRepository.findByUser(user);
        return posts;
    }

    @Override
    public PostResponse getAllPosts(int pageNumber, int pageSize,String sortBy,String sortDir) {
      Sort sort=  (sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());

        Pageable pageable=PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> post=postRepository.findAll(pageable);
        List<Post> allPost = post.getContent();
        PostResponse response = PostResponse.builder().content(allPost).pageNumber(post.getNumber()).pageSize(post.getSize()).totalPages(post.getTotalPages())
                .totalElements(post.getNumberOfElements()).lastPage(post.isLast()).build();
        return response;
    }

    @Override
    public Post getPostByPostId(int postId) {
        Post posts=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        return posts;
    }

    @Override
    public void deletePost(int postId) {
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        postRepository.delete(post);
    }

    @Override
    public Post updatePost(PostRequest postRequest, int postId) {
       Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","id",postId));
        post.setDate(LocalDateTime.now());
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setImageName(postRequest.getImageName());
        return postRepository.save(post);
    }

    @Override
    public List<Post> seachPost(String title) {
       List<Post> posts= postRepository.findByTitleContaining(title);
        return posts;
    }
}
