package com.blog.blogserviceapp.payloads.response;

import com.blog.blogserviceapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponse<T> {
    private String message;
    private boolean status;
    private T body;

    public BaseResponse(String message,boolean status, T body) {
        this.message=message;
        this.status=status;
        this.body=body;
    }
}
