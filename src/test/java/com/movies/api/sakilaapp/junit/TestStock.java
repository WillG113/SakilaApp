package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

class TestStock {

    Stock testStock = new Stock(1, 1);

    Stock stock = new Stock();

    @Test
    void testStockConstructor() {

        Assertions.assertEquals(1, testStock.getFilmID());
        Assertions.assertEquals(1, testStock.getStock());

    }

    @Test
    void testSetFilmID() {

        Assertions.assertEquals(1, testStock.getFilmID());
        testStock.setFilmID(5);
        Assertions.assertEquals(5, testStock.getFilmID());

    }

    @Test
    void testSetStock() {

        Assertions.assertEquals(1, testStock.getStock());
        testStock.setStock(5);
        Assertions.assertEquals(5, testStock.getStock());
    }

}
