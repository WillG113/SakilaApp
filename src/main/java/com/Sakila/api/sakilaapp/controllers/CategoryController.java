package com.Sakila.api.sakilaapp.controllers;

import com.Sakila.api.sakilaapp.*;
import com.Sakila.api.sakilaapp.resources.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class CategoryController extends SakilaAppApplication {

    private final CategoryRepository categoryRepository;
    private final CategoryFilmRepository categoryFilmRepository;

    public CategoryController(ActorRepository actorRepository, FilmRespository filmRespository, CategoryRepository categoryRepository, FilmActorRepository filmActorRepository, CategoryFilmRepository categoryFilmRepository) {
        super(actorRepository, filmRespository, categoryRepository, filmActorRepository, categoryFilmRepository);
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
