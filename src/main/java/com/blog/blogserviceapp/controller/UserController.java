package com.blog.blogserviceapp.controller;

import com.blog.blogserviceapp.model.User;
import com.blog.blogserviceapp.payloads.request.UserRequest;
import com.blog.blogserviceapp.payloads.response.BaseResponse;
import com.blog.blogserviceapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<BaseResponse<User>> createUser(@Valid @RequestBody UserRequest userRequest){
       User user= userService.createUser(userRequest);
        BaseResponse<User> response = new BaseResponse<>("User created successfully", true, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users")
    public ResponseEntity<?>getAllUsers(){
        List<User> user=userService.getAllUsers();
        return ResponseEntity.ok(new BaseResponse<>("List of all users",true,user));

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?>getUserById(@PathVariable int id){
        User user=userService.findUserById(id);
        return ResponseEntity.ok(new BaseResponse<>("Fetching User Successfully",true,user));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?>updateUser(@PathVariable int id, @RequestBody @Valid UserRequest userRequest){
        User user= userService.updateUser(id,userRequest);
        return ResponseEntity.ok(new BaseResponse<>("User Update Successfully",true,user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        User user= userService.deleteUser(id);
        return ResponseEntity.ok(new BaseResponse<>("User Delete Successfully",true,user));
    }

}
