package com.example.demo.dao;

import com.example.demo.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDAO {
    public List<Category> selectcategory();
}
