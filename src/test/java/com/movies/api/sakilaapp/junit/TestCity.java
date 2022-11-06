package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

public class TestCity {

    Country testCountry = new Country(1, "TestCountry");
    City testCity = new City(1, "TestCity", 1, testCountry);

    City city = new City();

    @Test
    public void testCityConstructor() {

        Assertions.assertEquals(1, testCity.getCityID());
        Assertions.assertEquals("TestCity", testCity.getCityName());
        Assertions.assertEquals(1, testCity.getCountryID());
        Assertions.assertEquals(testCountry, testCity.getCountry());

    }

    @Test
    public void testSetCityID() {

        Assertions.assertEquals(1, testCity.getCityID());
        testCity.setCityID(5);
        Assertions.assertEquals(5, testCity.getCityID());

    }

    @Test
    public void testSetCityName() {

        Assertions.assertEquals("TestCity", testCity.getCityName());
        testCity.setCityName("Test Two");
        Assertions.assertEquals("Test Two", testCity.getCityName());

    }

    @Test
    public void testSetCountryID() {

        Assertions.assertEquals(1, testCity.getCountryID());
        testCity.setCountryID(5);
        Assertions.assertEquals(5, testCity.getCountryID());

    }

    @Test
    public void testSetCountry() {

        Country testCountry2 = new Country(2, "Test Country Two");

        Assertions.assertEquals(testCountry, testCity.getCountry());
        testCity.setCountry(testCountry2);
        Assertions.assertEquals(testCountry2, testCity.getCountry());

    }


}
