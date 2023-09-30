package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class Category {
    private int idx;
    private String id;
    private String name;
    private String parent_id;
    private List<Category> children;
}
