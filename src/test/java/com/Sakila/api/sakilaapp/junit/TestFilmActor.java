package com.Sakila.api.sakilaapp.junit;

import com.Sakila.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

public class TestFilmActor {

    Actor testActor = new Actor("testFname", "testSname");

    FilmActor testFA = new FilmActor(3, 3, testActor);

    FilmActor filmActor = new FilmActor();

    @Test
    public void testFilmActorConstructor() {

        Assertions.assertEquals(3, testFA.getFilmID());
        Assertions.assertEquals(3, testFA.getActorID());
        Assertions.assertEquals(testActor, testFA.getActor());

    }

    @Test
    public void testSetFilmID() {

        Assertions.assertEquals(3, testFA.getFilmID());
        testFA.setFilmID(5);
        Assertions.assertEquals(5, testFA.getFilmID());

    }

    @Test
    public void testSetActorID() {

        Assertions.assertEquals(3, testFA.getActorID());
        testFA.setActorID(5);
        Assertions.assertEquals(5, testFA.getActorID());

    }

    @Test
    public void testSetActor() {

        Actor newActor = new Actor("newFname", "newSname");

        Assertions.assertEquals(testActor, testFA.getActor());
        testFA.setActor(newActor);
        Assertions.assertEquals(newActor, testFA.getActor());

    }

}
