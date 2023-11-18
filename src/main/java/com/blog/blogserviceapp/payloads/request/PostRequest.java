package com.blog.blogserviceapp.payloads.request;

import com.blog.blogserviceapp.model.Category;
import com.blog.blogserviceapp.model.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostRequest {

    @NotEmpty(message = "title cannot be null or empty")
    private String title;
    @NotEmpty(message = "content cannot be null or empty")
    private String content;
    private String imageName;
    private User userRequest;
    private Category categoryRequest;
}
