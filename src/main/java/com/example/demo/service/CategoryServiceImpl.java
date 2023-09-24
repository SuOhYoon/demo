package com.example.demo.service;

import com.example.demo.dao.CategoryDAO;
import com.example.demo.entity.Category;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryDAO dao;
    @Override
    public List<Category> selectcategory() {
        List<Category> categories = dao.selectcategory();

        // 카테고리 ID를 기반으로 Map을 사용하여 카테고리를 그룹화
        Map<Integer, Category> categoryMap;
        categoryMap = new HashMap<>();
        for (Category category : categories) {
            categoryMap.put(category.getId(), category);
        }

        // 최상위 카테고리를 찾고, 자식 카테고리를 연결
        List<Category> categoryHierarchy = new ArrayList<>();
        for (Category category : categories) {
            Integer parentId = category.getParent_id();
            if (parentId == null) {
                categoryHierarchy.add(category);
            } else {
                Category parentCategory = categoryMap.get(parentId);
                if (parentCategory != null) {
                    if (parentCategory.getChildren() == null) {
                        parentCategory.setChildren(new ArrayList<>());
                    }
                    parentCategory.getChildren().add(category);
                }
            }
        }
        return categoryHierarchy;
    }
}
