package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

class TestRental {

    Film testFilm = new Film("testTitle", "testDesc", "2006", 5, 0.99, 25, 20.99, "PG", 0);
    Rental testRental = new Rental(1, 1);

    Rental rental = new Rental();

    @Test
    void testRentalConstructor() {

        Assertions.assertEquals(1, testRental.getRentalID());
        Assertions.assertEquals(1, testRental.getCustomerID());


    }

    @Test
    void testSetRentalID() {

        Assertions.assertEquals(1, testRental.getRentalID());
        testRental.setRentalID(5);
        Assertions.assertEquals(5, testRental.getRentalID());

    }

    @Test
    void testSetCustomerID() {

        Assertions.assertEquals(1, testRental.getCustomerID());
        testRental.setCustomerID(5);
        Assertions.assertEquals(5, testRental.getCustomerID());

    }


}
