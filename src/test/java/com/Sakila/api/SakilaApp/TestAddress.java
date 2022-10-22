package com.Sakila.api.SakilaApp;

import org.junit.jupiter.api.*;

public class TestAddress {

    Address testAddress = new Address("testAddress", "testDistrict", "testPostcode", "testID");

    @Test
    public void testAddressConstructor() {

        Assertions.assertEquals("testAddress", testAddress.getAddress());
        Assertions.assertEquals("testDistrict", testAddress.getDistrict());
        Assertions.assertEquals("testPostcode", testAddress.getPostcode());
        Assertions.assertEquals("testID", testAddress.getCityID());

    }

    @Test
    public void testSetAddress() {

        Assertions.assertEquals("testAddress", testAddress.getAddress());
        testAddress.setAddress("newTestAddress");
        Assertions.assertEquals("newTestAddress", testAddress.getAddress());

    }

    @Test
    public void testSetDistrict() {

        Assertions.assertEquals("testDistrict", testAddress.getDistrict());
        testAddress.setDistrict("newTestDistrict");
        Assertions.assertEquals("newTestDistrict", testAddress.getDistrict());

    }

    @Test
    public void testSetPostcode() {

        Assertions.assertEquals("testPostcode", testAddress.getPostcode());
        testAddress.setPostcode("newTestPostcode");
        Assertions.assertEquals("newTestPostcode", testAddress.getPostcode());

    }

    @Test
    public void testSetCityID() {

        Assertions.assertEquals("testID", testAddress.getCityID());
        testAddress.setCityID("newTestID");
        Assertions.assertEquals("newTestID", testAddress.getCityID());

    }
}
