package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
// import lombok.AllArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@AllArgsConstructor
public class CategoryController {
    private final CategoryService service;
    @GetMapping("/")
    public List<Map<String, Object>> displayData(Model model) {
        int count = 0;
        int ccount = 0;
        List<Category> dblist = service.findAll();
        // 서비스에서 가져온 부모 리스트의 이름을 새로운 리스트로 저장
        List<Category> parent = new ArrayList<>();
        List<Category> child = new ArrayList<>();
        for (Category item : dblist) {
            if (item.getParent_id() == null) {
                parent.add(item); // parent_id가 없는 경우 parent 리스트에 추가
            } else {
                child.add(item); // parent_id가 있는 경우 child 리스트에 추가
            }
        }

        // parent 카테고리 출력
        System.out.println("Parent Categories:");
        for (Category item : parent) {
            System.out.print(parent);
        }

        // child 카테고리 출력
        System.out.println("\nChild Categories:");
        for (Category item : child) {
            System.out.print(child);
        }
        // 서비스에서 가져온 자식 리스트의 이름을 새로운 리스트로 저장
        // 서비스에서 가져온 값 리스트의 이름을 새로운 리스트로 저장
        // JSON 데이터 생성
        List<Map<String, Object>> jsonData = new ArrayList<>();

        // 데이터 생성
        int i = 0;
        for (Category key : parent) {
            Map<String, Object> data = new HashMap<>();
            List<Map<String, List<String>>> dataList = new ArrayList<>();

            for (Category item : child) {
                Map<String, List<String>> subData = new HashMap<>();
                List<String> values = new ArrayList<>();
                values.add(item.getName() + i);

                subData.put(item.getName(), values);
                dataList.add(subData);
            }

            data.put(String.valueOf(key.getName()), dataList);
            jsonData.add(data);
            i++;
        }

        // JSON 데이터를 모델에 추가
        model.addAttribute("jsonData", jsonData);

        return jsonData;
    }
}