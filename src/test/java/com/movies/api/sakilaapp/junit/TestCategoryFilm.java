package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

class TestCategoryFilm {

    Film testFilm = new Film("TestFilm", "Test Description", "2000", 3, 2.99, 30, 20.99, "PG", 0, new Stock(), new CategoryFilm());
    CategoryFilm testCategoryFilm = new CategoryFilm(1, 1);

    CategoryFilm categoryFilm = new CategoryFilm();

    @Test
    void testCategoryFilmConstructor() {

        Assertions.assertEquals(1, testCategoryFilm.getFilmID());
        Assertions.assertEquals(1, testCategoryFilm.getCategoryID());

    }

    @Test
    void testCategoryFilmSetFilmID() {

        Assertions.assertEquals(1, testCategoryFilm.getFilmID());
        testCategoryFilm.setFilmID(5);
        Assertions.assertEquals(5, testCategoryFilm.getFilmID());

    }

    @Test
    void setTestCategoryFilmSetCategoryID() {

        Assertions.assertEquals(1, testCategoryFilm.getCategoryID());
        testCategoryFilm.setCategoryID(5);
        Assertions.assertEquals(5, testCategoryFilm.getCategoryID());


    }

    @Test
    void setTestCategoryFilmSetFilm() {

        Film f1 = new Film("Title", "Desc", "2000", 1, 1.99, 10, 1.99, "PG", 0, new Stock(), new CategoryFilm());

        testCategoryFilm.setFilm(f1);
        Assertions.assertEquals(f1, testCategoryFilm.getFilm());

    }

    @Test
    void setTestCategoryFilmSetCategory() {

        Category c1 = new Category(1, "Test");

        testCategoryFilm.setCategory(c1);
        Assertions.assertEquals(c1, testCategoryFilm.getCategory());

    }



}
