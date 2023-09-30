package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
// import lombok.AllArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.regex.Pattern;

@Controller
@AllArgsConstructor
public class CategoryController {
    private final CategoryService service;
    @GetMapping("/")
    public String displayData(Model model) {
        List<Category> dblist = service.findAll();

        // 데이터 생성
        List<Map<String, Object>> jsonData = new ArrayList<>();

        for (Category parent : dblist) {
            if (parent.getParent_id() == null) {
                Map<String, Object> parentData = new HashMap<>();
                parentData.put("name", parent.getName());

                List<Map<String, Object>> childrenData = new ArrayList<>();

                for (Category child : dblist) {
                    if (child.getParent_id() != null && child.getParent_id().equals(parent.getId())) {
                        Map<String, Object> childData = new HashMap<>();
                        childData.put("name", child.getName());

                        List<Map<String, Object>> lastChildrenData = new ArrayList<>();

                        for (Category lastChild : dblist) {
                            if (lastChild.getParent_id() != null && lastChild.getParent_id().equals(child.getId())) {
                                Map<String, Object> lastChildData = new HashMap<>();
                                lastChildData.put("name", lastChild.getName());
                                lastChildrenData.add(lastChildData);
                            }
                        }

                        if (!lastChildrenData.isEmpty()) {
                            childData.put("children", lastChildrenData);
                            childrenData.add(childData);
                        }
                    }
                }

                if (!childrenData.isEmpty()) {
                    parentData.put("children", childrenData);
                    jsonData.add(parentData);
                }
            }
        }

        // JSON 데이터를 모델에 추가
        model.addAttribute("jsonData", jsonData);

        return "test";
    }
}