package com.example.demo.service;

import com.example.demo.dao.CategoryDAO;
import com.example.demo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// CategoryService.java
@Service
public class CategoryService {
    @Autowired
    private CategoryDAO categoryMapper;

    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    public Category findById(Long id) {
        return categoryMapper.findById(id);
    }

    public List<Category> findByParentId(Long parentId) {
        return categoryMapper.findByParentId(parentId);
    }
}
