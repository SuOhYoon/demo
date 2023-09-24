package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Controller
@AllArgsConstructor
public class CategoryController {
    private final CategoryService service;
    @GetMapping("/")
    public String getAllCategories(Model model) {
        List<Category> categories = service.selectcategory();

        // 계층 구조를 구성하기 위한 처리
        List<Category> hierarchicalCategories = buildHierarchy(categories, null, 0);

        model.addAttribute("categories", hierarchicalCategories);
        return "test";
    }

    private List<Category> buildHierarchy(List<Category> categories, Integer parentId, int level) {
        List<Category> hierarchicalCategories = new ArrayList<>();

        for (Category category : categories) {
            if (Objects.equals(category.getParent_id(), parentId)) {
                category.setLevel(level);
                category.setChildren(buildHierarchy(categories, category.getId(), level + 1));
                hierarchicalCategories.add(category);
            }
        }

        return hierarchicalCategories;
    }
}
