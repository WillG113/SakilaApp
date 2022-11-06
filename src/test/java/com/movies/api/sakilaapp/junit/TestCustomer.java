package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

class TestCustomer {

    Country testCountry = new Country(1, "TestCountry");
    City testCity = new City(1, "TestCity", 1, testCountry);
    Address testAddress = new Address(1, "Test Address", "Test District", 1, "Test Postcode", "12345", testCity);
    Customer testCustomer = new Customer(1, "FName", "SName", "Test Email", 1, 1, testAddress);

    Customer customer = new Customer();

    @Test
    void testCustomerConstructor() {

        Assertions.assertEquals(1, testCustomer.getCustomerID());
        Assertions.assertEquals("FName", testCustomer.getFname());
        Assertions.assertEquals("SName", testCustomer.getLname());
        Assertions.assertEquals("Test Email", testCustomer.getEmail());
        Assertions.assertEquals(1, testCustomer.getAddressID());
        Assertions.assertEquals(1, testCustomer.getUserActive());
        Assertions.assertEquals(testAddress, testCustomer.getAddress());

    }

    @Test
    void testSetCustomerID() {

        Assertions.assertEquals(1, testCustomer.getCustomerID());
        testCustomer.setCustomerID(5);
        Assertions.assertEquals(5, testCustomer.getCustomerID());

    }

    @Test
    void testSetCustomerFName() {

        Assertions.assertEquals("FName", testCustomer.getFname());
        testCustomer.setFname("FName Two");
        Assertions.assertEquals("FName Two", testCustomer.getFname());

    }

    @Test
    void testSetCustomerSName() {

        Assertions.assertEquals("SName", testCustomer.getLname());
        testCustomer.setLname("SName Two");
        Assertions.assertEquals("SName Two", testCustomer.getLname());

    }

    @Test
    void testSetCustomerEmail() {

        Assertions.assertEquals("Test Email", testCustomer.getEmail());
        testCustomer.setEmail("Test Email 2");
        Assertions.assertEquals("Test Email 2", testCustomer.getEmail());

    }

    @Test
    void testSetCustomerAddressID() {

        Assertions.assertEquals(1, testCustomer.getAddressID());
        testCustomer.setAddressID(5);
        Assertions.assertEquals(5, testCustomer.getAddressID());

    }

    @Test
    void testSetCustomerUserActive() {

        Assertions.assertEquals(1, testCustomer.getUserActive());
        testCustomer.setUserActive(0);
        Assertions.assertEquals(0, testCustomer.getUserActive());

    }

    @Test
    void testSetCustomerAddress() {

        Address testAddress2 = new Address(2, "Test Address 2", "Test District 2", 2, "Test Postcode 2", "11111", testCity);

        Assertions.assertEquals(testAddress, testCustomer.getAddress());
        testCustomer.setAddress(testAddress2);
        Assertions.assertEquals(testAddress2, testCustomer.getAddress());

    }

}
