package com.blog.blogserviceapp.repository;

import com.blog.blogserviceapp.model.Category;
import com.blog.blogserviceapp.model.Post;
import com.blog.blogserviceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
   List<Post>  findByCategory(Category category);

    List<Post> findByUser(User userId);

    List<Post> findByTitleContaining(String title);
}
