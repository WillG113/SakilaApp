package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

class TestFilmActor {

    Actor testActor = new Actor("testFname", "testSname");

    FilmActor testFA = new FilmActor(3, 3, testActor);

    FilmActor filmActor = new FilmActor();

    @Test
    void testFilmActorConstructor() {

        Assertions.assertEquals(3, testFA.getFilmID());
        Assertions.assertEquals(3, testFA.getActorID());
        Assertions.assertEquals(testActor, testFA.getActor());

    }

    @Test
    void testSetFilmID() {

        Assertions.assertEquals(3, testFA.getFilmID());
        testFA.setFilmID(5);
        Assertions.assertEquals(5, testFA.getFilmID());

    }

    @Test
    void testSetActorID() {

        Assertions.assertEquals(3, testFA.getActorID());
        testFA.setActorID(5);
        Assertions.assertEquals(5, testFA.getActorID());

    }

    @Test
    void testSetActor() {

        Actor newActor = new Actor("newFname", "newSname");

        Assertions.assertEquals(testActor, testFA.getActor());
        testFA.setActor(newActor);
        Assertions.assertEquals(newActor, testFA.getActor());

    }

}
