package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.controllers.*;
import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestStockController {

    StockRepository stockRepository = mock(StockRepository.class);
    StockController mockApp = new StockController(stockRepository);

    @Test
    void testLowerScore() {

        String output = mockApp.lowerStockByID(1);
        Assertions.assertEquals("stock updated", output);

    }

    @Test
    void testIncreaseScore() {

        String output = mockApp.increaseStockByID(1);
        Assertions.assertEquals("stock updated", output);

    }

}
