package com.Sakila.api.SakilaApp;

import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class TestFilm {

    Film testFilm = new Film("testTitle", "testDesc", 12);

    Film film = new Film();

    @Test
    public void testFilmConstructor() {

        Assertions.assertEquals("testTitle", testFilm.getTitle());
        Assertions.assertEquals("testDesc", testFilm.getDesc());
        Assertions.assertEquals(12, testFilm.getLength());

    }

    @Test
    public void testSetFilmID() {

        Assertions.assertEquals(0, testFilm.getFilmID());
        testFilm.setFilmID(3);
        Assertions.assertEquals(3, testFilm.getFilmID());

    }

    @Test
    public void testSetTitle() {

        Assertions.assertEquals("testTitle", testFilm.getTitle());
        testFilm.setTitle("newTitle");
        Assertions.assertEquals("newTitle", testFilm.getTitle());

    }

    @Test
    public void testSetDesc() {

        Assertions.assertEquals("testDesc", testFilm.getDesc());
        testFilm.setDesc("newDesc");
        Assertions.assertEquals("newDesc", testFilm.getDesc());

    }

    @Test
    public void testSetLength() {

        Assertions.assertEquals(12, testFilm.getLength());
        testFilm.setLength(3);
        Assertions.assertEquals(3, testFilm.getLength());

    }

}
