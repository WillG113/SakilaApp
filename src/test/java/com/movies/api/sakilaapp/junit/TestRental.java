package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

class TestRental {

    Rental testRental = new Rental(1, 5);

    Rental rental = new Rental();

    @Test
    void testRentalConstructor() {

        Assertions.assertEquals(5, testRental.getFilmID());
        Assertions.assertEquals(1, testRental.getCustomerID());


    }

    @Test
    void testSetFilmID() {

        Assertions.assertEquals(5, testRental.getFilmID());
        testRental.setFilmID(12);
        Assertions.assertEquals(12, testRental.getFilmID());

    }

    @Test
    void testSetCustomerID() {

        Assertions.assertEquals(1, testRental.getCustomerID());
        testRental.setCustomerID(5);
        Assertions.assertEquals(5, testRental.getCustomerID());

    }


}
