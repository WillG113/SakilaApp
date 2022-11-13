package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

class TestCategoryFilm {

    Film testFilm = new Film("TestFilm", "Test Description", "2000", 3, 2.99, 30, 20.99, "PG", 0);
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



}
