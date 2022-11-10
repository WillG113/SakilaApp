package com.movies.api.sakilaapp.controllers;

import com.movies.api.sakilaapp.resources.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@CrossOrigin
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryFilmRepository categoryFilmRepository;

    public CategoryController(CategoryRepository categoryRepository, CategoryFilmRepository categoryFilmRepository) {
       this.categoryRepository = categoryRepository;
        this.categoryFilmRepository = categoryFilmRepository;
    }

    @GetMapping("/api/categories")
    public Iterable<Category> getAllCategoriesAPI() {
        return categoryRepository.findAll();
    }

    @GetMapping("/api/filmcategories/{id}")
    public Iterable<CategoryFilm> getFilmsByCategoryAPI(@PathVariable int id) {
        return categoryFilmRepository.findByCategoryID(id);
    }
}
