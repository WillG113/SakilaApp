package com.Sakila.api.sakilaapp.junit;


import com.Sakila.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

class TestActor {

    Actor testActor = new Actor("TestFirstname", "TestLastname");

    Actor actor = new Actor();

    @Test
    void testActorConstructor() {

        Assertions.assertEquals("TestFirstname", testActor.getActorFirstname(), "Should automatically set the value based on what's passed into constructor");
        Assertions.assertEquals("TestLastname", testActor.getActorLastname(), "Should automatically set the value based on what's passed into constructor");

    }

    @Test
    void testSetActorID() {

        Assertions.assertEquals(0, testActor.getActorID());
        testActor.setActorID(3);
        Assertions.assertEquals(3, testActor.getActorID());

    }

    @Test
    void testSetActorFirstname() {

        Assertions.assertEquals("TestFirstname", testActor.getActorFirstname());
        testActor.setActorFirstname("newTestName");
        Assertions.assertEquals("newTestName", testActor.getActorFirstname());

    }

    @Test
    void testSetActorLastname() {

        Assertions.assertEquals("TestLastname", testActor.getActorLastname());
        testActor.setActorLastname("newTestName");
        Assertions.assertEquals("newTestName", testActor.getActorLastname());

    }



}
