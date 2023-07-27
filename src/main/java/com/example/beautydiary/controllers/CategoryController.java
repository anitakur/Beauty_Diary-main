package com.example.beautydiary.controllers;

import com.example.beautydiary.entities.Category;
import com.example.beautydiary.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String findAllCategories(Model model ){
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }
}
