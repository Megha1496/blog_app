package com.blog.blogserviceapp.service.impl;

import com.blog.blogserviceapp.exceptionHandling.ResourceNotFoundException;
import com.blog.blogserviceapp.model.User;
import com.blog.blogserviceapp.payloads.request.UserRequest;
import com.blog.blogserviceapp.repository.UserRepository;
import com.blog.blogserviceapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User createUser(UserRequest userRequest) {
        User user=this.modelMapper.map(userRequest,User.class);
       User savedUser= this.userRepository.save(user);
        return savedUser;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users= userRepository.findAll();
        return users;
    }

    @Override
    public User findUserById(int id) {
        User user= userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user","id",id));
        return user;
    }

    @Override
    public User updateUser(int id, UserRequest userRequest) {
      User user=  userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setAbout(userRequest.getAbout());
      return userRepository.save(user);
    }

    @Override
    public User deleteUser(int id) {
        User user=  userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));
        userRepository.delete(user);
        return user;
    }
}
