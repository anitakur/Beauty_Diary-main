package com.example.beautydiary.services;

import com.example.beautydiary.entities.Category;
import com.example.beautydiary.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }
}
