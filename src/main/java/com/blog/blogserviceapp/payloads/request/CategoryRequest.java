package com.blog.blogserviceapp.payloads.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {

    @NotEmpty(message = "title can not be null or empty")
    private String title;

    @NotEmpty(message = "description can not be null or empty")
    private String description;
}
