package com.Sakila.api.sakilaapp.junit;

import com.Sakila.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

public class TestCategoryFilm {

    Film testFilm = new Film("TestFilm", "Test Description", "2000", 3, 2.99, 30, 20.99, "PG", 0);
    CategoryFilm testCategoryFilm = new CategoryFilm(1, 1, testFilm);

    CategoryFilm categoryFilm = new CategoryFilm();

    @Test
    public void testCategoryFilmConstructor() {

        Assertions.assertEquals(1, testCategoryFilm.getFilmID());
        Assertions.assertEquals(1, testCategoryFilm.getCategoryID());
        Assertions.assertEquals(testFilm, testCategoryFilm.getFilm());

    }

    @Test
    public void testCategoryFilmSetFilmID() {

        Assertions.assertEquals(1, testCategoryFilm.getFilmID());
        testCategoryFilm.setFilmID(5);
        Assertions.assertEquals(5, testCategoryFilm.getFilmID());

    }

    @Test
    public void setTestCategoryFilmSetCategoryID() {

        Assertions.assertEquals(1, testCategoryFilm.getCategoryID());
        testCategoryFilm.setCategoryID(5);
        Assertions.assertEquals(5, testCategoryFilm.getCategoryID());


    }

    @Test
    public void setTestCategoryFilmSetFilm() {

        Film otherTestFilm = new Film("OtherTestFilm", "Test Description", "2010", 5, 12.99, 60, 25.99, "PG", 0);

        Assertions.assertEquals(testFilm, testCategoryFilm.getFilm());
        testCategoryFilm.setFilm(otherTestFilm);
        Assertions.assertEquals(otherTestFilm, testCategoryFilm.getFilm());

    }


}
