package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Category {
    Integer id;
    String name;
    Integer parent_id;
    int level;
    private List<Category> children;
}
