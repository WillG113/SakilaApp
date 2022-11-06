package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

class TestCountry {

    Country testCountry = new Country(1, "TestCountry");

    Country country = new Country();

    @Test
    void testCountryConstructor() {

        Assertions.assertEquals(1, testCountry.getCountryID());
        Assertions.assertEquals("TestCountry", testCountry.getCountryName());

    }

    @Test
    void testSetCountryID() {

        Assertions.assertEquals(1, testCountry.getCountryID());
        testCountry.setCountryID(5);
        Assertions.assertEquals(5, testCountry.getCountryID());

    }

    @Test
    void testSetCountryName() {

        Assertions.assertEquals("TestCountry", testCountry.getCountryName());
        testCountry.setCountryName("Test Two");
        Assertions.assertEquals("Test Two", testCountry.getCountryName());

    }
}
