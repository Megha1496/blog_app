package com.blog.blogserviceapp.payloads.response;

import com.blog.blogserviceapp.model.Post;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {
    private List<Post> content;
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean lastPage;
}
