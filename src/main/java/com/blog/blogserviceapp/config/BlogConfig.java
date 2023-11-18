package com.blog.blogserviceapp.config;

import com.blog.blogserviceapp.model.Category;
import com.blog.blogserviceapp.model.User;
import com.blog.blogserviceapp.payloads.request.CategoryRequest;
import com.blog.blogserviceapp.payloads.request.UserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlogConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


}
