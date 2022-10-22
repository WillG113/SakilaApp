package com.Sakila.api.SakilaApp;

import org.junit.jupiter.api.*;

public class TestActor {

    Actor testActor = new Actor("TestFirstname", "TestLastname");


    @Test
    public void testActorConstructor() {

        Assertions.assertEquals("TestFirstname", testActor.getActorFirstname(), "Should automatically set the value based on what's passed into constructor");
        Assertions.assertEquals("TestLastname", testActor.getActorLastname(), "Should automatically set the value based on what's passed into constructor");

    }

    @Test
    public void testSetActorID() {

        Assertions.assertEquals(0, testActor.getActorID());
        testActor.setActorID(3);
        Assertions.assertEquals(3, testActor.getActorID());

    }

    @Test
    public void testSetActorFirstname() {

        Assertions.assertEquals("TestFirstname", testActor.getActorFirstname());
        testActor.setActorFirstname("newTestName");
        Assertions.assertEquals("newTestName", testActor.getActorFirstname());

    }

    @Test
    public void testSetActorLastname() {

        Assertions.assertEquals("TestLastname", testActor.getActorLastname());
        testActor.setActorLastname("newTestName");
        Assertions.assertEquals("newTestName", testActor.getActorLastname());

    }



}
