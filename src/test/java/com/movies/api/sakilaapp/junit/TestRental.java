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
    void testSetRentalID() {

        testRental.setRentalID(12);
        Assertions.assertEquals(12, testRental.getRentalID());

    }

    @Test
    void testSetRentalDate() {

        testRental.setRentalDate("111");
        Assertions.assertEquals("111", testRental.getRentalDate());

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

    @Test
    void testSetReturnDate() {

        testRental.setReturnDate("111");
        Assertions.assertEquals("111", testRental.getReturnDate());

    }

    @Test
    void testSetFilm() {

        Film f1 = new Film("Title", "Desc", "2000", 1, 1.99, 10, 1.99, "PG", 0, new Stock(), new CategoryFilm());

        testRental.setFilm(f1);
        Assertions.assertEquals(f1, testRental.getFilm());

    }


}
