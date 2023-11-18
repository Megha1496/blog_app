package com.blog.blogserviceapp.payloads.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @NotEmpty(message = "username must be minimum of 4 characters ")
    private String name;

    @NotEmpty(message = "email cannot be null or empty")
    @Email(message = "Email Address is not valid")
    private String email;

    @NotEmpty(message = "password is mandatory")
    @Min(value = 4,message = "Password must be minimum of 4 characters and max 10 characters")
    private String password;

    @NotEmpty(message = "about can't be null or empty")
    private String about;
}
