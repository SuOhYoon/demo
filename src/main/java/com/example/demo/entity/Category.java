package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class Category {
    private int id;
    private String name;
    private Integer parent_id;
    private
    private List<Category> children;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parent_id +
                ", children=" + children +
                '}';
    }
}
