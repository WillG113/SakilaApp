package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

class TestAddress {

    Country testCountry = new Country(1, "TestCountry");
    City testCity = new City(1, "TestCity", 1, testCountry);
    Address testAddress = new Address(1, "Test Address", "Test District", 1, "Test Postcode", "12345", testCity);

    Address address = new Address();

    @Test
    void testAddressConstructor() {

        Assertions.assertEquals(1, testAddress.getAddressID());
        Assertions.assertEquals("Test Address", testAddress.getAddressLine());
        Assertions.assertEquals("Test District", testAddress.getDistrictName());
        Assertions.assertEquals(1, testAddress.getCityID());
        Assertions.assertEquals("Test Postcode", testAddress.getPostcode());
        Assertions.assertEquals("12345", testAddress.getPhoneNo());
        Assertions.assertEquals(testCity, testAddress.getCity());

    }

    @Test
    void testSetAddressID() {

        Assertions.assertEquals(1, testAddress.getAddressID());
        testAddress.setAddressID(5);
        Assertions.assertEquals(5, testAddress.getAddressID());

    }

    @Test
    void testSetAddressLine() {

        Assertions.assertEquals("Test Address", testAddress.getAddressLine());
        testAddress.setAddressLine("Test Two");
        Assertions.assertEquals("Test Two", testAddress.getAddressLine());

    }

    @Test
    void testSetDistrict() {

        Assertions.assertEquals("Test District", testAddress.getDistrictName());
        testAddress.setDistrictName("Test Two");
        Assertions.assertEquals("Test Two", testAddress.getDistrictName());

    }

    @Test
    void testSetCityID() {

        Assertions.assertEquals(1, testAddress.getCityID());
        testAddress.setCityID(5);
        Assertions.assertEquals(5, testAddress.getCityID());

    }

    @Test
    void testSetPostcode() {

        Assertions.assertEquals("Test Postcode", testAddress.getPostcode());
        testAddress.setPostcode("Test Two");
        Assertions.assertEquals("Test Two", testAddress.getPostcode());

    }

    @Test
    void testSetPhone() {

        Assertions.assertEquals("12345", testAddress.getPhoneNo());
        testAddress.setPhoneNo("11111");
        Assertions.assertEquals("11111", testAddress.getPhoneNo());

    }

    @Test
    void testSetCity() {

        City testCity2 = new City(2, "TestCity Two", 2, testCountry);

        Assertions.assertEquals(testCity, testAddress.getCity());
        testAddress.setCity(testCity2);
        Assertions.assertEquals(testCity2, testAddress.getCity());

    }

}
