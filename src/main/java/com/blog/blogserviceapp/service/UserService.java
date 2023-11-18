package com.blog.blogserviceapp.service;

import com.blog.blogserviceapp.model.User;
import com.blog.blogserviceapp.payloads.request.UserRequest;

import java.util.List;

public interface UserService {
    User createUser(UserRequest userRequest) ;

    List<User> getAllUsers();

    User findUserById(int id);

    User updateUser(int id, UserRequest userRequest);

    User deleteUser(int id);
}
