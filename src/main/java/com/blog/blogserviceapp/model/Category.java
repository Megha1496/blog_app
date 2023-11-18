package com.blog.blogserviceapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    @JsonIgnore
    private List<Post> categoryPost=new ArrayList<>();
}
