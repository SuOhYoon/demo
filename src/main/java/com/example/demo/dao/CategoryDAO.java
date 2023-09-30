package com.example.demo.dao;

import com.example.demo.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDAO {
    List<Category> findAll();

    Category findById(Long id);

    List<Category> findByParentId(Long parentId);
}
