package com.blog.blogserviceapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "email_id")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "description")
    private String about;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    @JsonIgnore
    private List<Post> userPost=new ArrayList<>();
}
