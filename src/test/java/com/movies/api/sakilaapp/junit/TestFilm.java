package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

class TestFilm {

    Film testFilm = new Film("testTitle", "testDesc", "2006", 5, 0.99, 25, 20.99, "PG", 0);

    Film film = new Film();

    @Test
    void testFilmConstructor() {

        Assertions.assertEquals("testTitle", testFilm.getTitle());
        Assertions.assertEquals("testDesc", testFilm.getDesc());
        Assertions.assertEquals(25, testFilm.getLength());
        Assertions.assertEquals(0, testFilm.getScore());

    }

    @Test
    void testSetFilmID() {

        Assertions.assertEquals(0, testFilm.getFilmID());
        testFilm.setFilmID(3);
        Assertions.assertEquals(3, testFilm.getFilmID());

    }

    @Test
    void testSetTitle() {

        Assertions.assertEquals("testTitle", testFilm.getTitle());
        testFilm.setTitle("newTitle");
        Assertions.assertEquals("newTitle", testFilm.getTitle());

    }

    @Test
    void testSetDesc() {

        Assertions.assertEquals("testDesc", testFilm.getDesc());
        testFilm.setDesc("newDesc");
        Assertions.assertEquals("newDesc", testFilm.getDesc());

    }

    @Test
    void testSetReleaseDate() {

        Assertions.assertEquals("2006", testFilm.getReleaseYear());
        testFilm.setReleaseYear("2001");
        Assertions.assertEquals("2001", testFilm.getReleaseYear());

    }

    @Test
    void testSetRentDuration() {

        Assertions.assertEquals(5, testFilm.getRentDuration());
        testFilm.setRentDuration(9);
        Assertions.assertEquals(9, testFilm.getRentDuration());

    }

    @Test
    void testSetRentRate() {

        Assertions.assertEquals(0.99, testFilm.getRentRate());
        testFilm.setRentRate(3.99);
        Assertions.assertEquals(3.99, testFilm.getRentRate());

    }

    @Test
    void testSetReplacementCost() {

        Assertions.assertEquals(20.99, testFilm.getReplacementCost());
        testFilm.setReplacementCost(23.99);
        Assertions.assertEquals(23.99, testFilm.getReplacementCost());

    }

    @Test
    void testSetRating() {

        Assertions.assertEquals("PG", testFilm.getRating());
        testFilm.setRating("18");
        Assertions.assertEquals("18", testFilm.getRating());

    }

    @Test
    void testSetLength() {

        Assertions.assertEquals(25, testFilm.getLength());
        testFilm.setLength(3);
        Assertions.assertEquals(3, testFilm.getLength());

    }

    @Test
    void testSetScore() {

        Assertions.assertEquals(0, testFilm.getScore());
        testFilm.setScore(5);
        Assertions.assertEquals(5, testFilm.getScore());

    }

    @Test
    void testSetStock() {

        Stock newStock = new Stock(1,1);

        Assertions.assertEquals(0, testFilm.getStock().getStock());
        testFilm.setStock(newStock);
        Assertions.assertEquals(newStock, testFilm.getStock());

    }

}
