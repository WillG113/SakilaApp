package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

public class TestRental {

    Film testFilm = new Film("testTitle", "testDesc", "2006", 5, 0.99, 25, 20.99, "PG", 0);
    Rental testRental = new Rental(1, "Test Date", 1, 1, "Test Date 2", testFilm);

    Rental rental = new Rental();

    @Test
    public void testRentalConstructor() {

        Assertions.assertEquals(1, testRental.getRentalID());
        Assertions.assertEquals("Test Date", testRental.getRentalDate());
        Assertions.assertEquals(1, testRental.getCustomerID());
        Assertions.assertEquals(1, testRental.getFilmID());
        Assertions.assertEquals("Test Date 2", testRental.getReturnDate());
        Assertions.assertEquals(1, testRental.getRentalID());

    }

    @Test
    public void testSetRentalID() {

        Assertions.assertEquals(1, testRental.getRentalID());
        testRental.setRentalID(5);
        Assertions.assertEquals(5, testRental.getRentalID());

    }

    @Test
    public void testSetRentalDate() {

        Assertions.assertEquals("Test Date", testRental.getRentalDate());
        testRental.setRentalDate("Date Two");
        Assertions.assertEquals("Date Two", testRental.getRentalDate());

    }

    @Test
    public void testSetCustomerID() {

        Assertions.assertEquals(1, testRental.getCustomerID());
        testRental.setCustomerID(5);
        Assertions.assertEquals(5, testRental.getCustomerID());

    }

    @Test
    public void testSetFilmID() {

        Assertions.assertEquals(1, testRental.getFilmID());
        testRental.setFilmID(5);
        Assertions.assertEquals(5, testRental.getFilmID());

    }

    @Test
    public void testSetReturnDate() {

        Assertions.assertEquals("Test Date 2", testRental.getReturnDate());
        testRental.setReturnDate("Date Two 2");
        Assertions.assertEquals("Date Two 2", testRental.getReturnDate());

    }

    @Test
    public void testSetFilm() {

        Assertions.assertEquals(1, testRental.getRentalID());
        testRental.setRentalID(5);
        Assertions.assertEquals(5, testRental.getRentalID());

    }

}
