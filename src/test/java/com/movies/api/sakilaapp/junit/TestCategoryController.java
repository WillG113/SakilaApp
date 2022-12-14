package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.controllers.*;
import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestCategoryController {

    CategoryRepository categoryRepository = mock(CategoryRepository.class);
    CategoryFilmRepository categoryFilmRepository = mock(CategoryFilmRepository.class);
    CategoryController mockApp = new CategoryController(categoryRepository, categoryFilmRepository);
    Film testFilm = new Film("testTitle", "testDesc", "2006", 5, 0.99, 25, 20.99, "PG", 0, new Stock(), new CategoryFilm());
    Film testFilm2 = new Film("testTitle2", "testDesc", "2006", 5, 0.99, 25, 20.99, "PG", 0, new Stock(), new CategoryFilm());
    Iterable<Film> allFilms = Arrays.asList(testFilm, testFilm2);
    List<Film> allFilms2 = Arrays.asList(testFilm, testFilm2);

    Actor testActor = new Actor("TestFName", "TestSName");
    Actor testActor2 = new Actor("TestFName2", "TestSName2");

    Iterable<Actor> allActors = Arrays.asList(testActor, testActor2);
    List<Actor> allActors2 = Arrays.asList(testActor, testActor2);


    // Categories ----------------------------------------

    @Test
    void testGetCategories() throws Exception {

        Category cat1 = new Category(1, "Test 1");
        Category cat2 = new Category(2, "Test 2");
        Iterable<Category> catList = Arrays.asList(cat1, cat2);
        List<Category> catList2 = Arrays.asList(cat1, cat2);

        when(categoryRepository.findAll()).thenReturn(catList2);

        Iterable<Category> actualResult = mockApp.getAllCategoriesAPI();

        Assertions.assertEquals(catList, actualResult);

    }

    // Films of certain Category --------------------------

    @Test
    void testGetFilmsByCategory() throws Exception {

        CategoryFilm cat1 = new CategoryFilm(1,1);
        CategoryFilm cat2 = new CategoryFilm(2,2);
        List<CategoryFilm> catList = Arrays.asList(cat1, cat2);

        when(categoryFilmRepository.findByCategoryID(1)).thenReturn(catList);

        Iterable<CategoryFilm> actualResult = mockApp.getFilmsByCategoryAPI(1);

        Assertions.assertEquals(catList, actualResult);

    }

}
