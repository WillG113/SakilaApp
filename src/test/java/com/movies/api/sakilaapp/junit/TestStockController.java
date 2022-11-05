package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.controllers.*;
import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestStockController {

    ActorRepository actorRepository = mock(ActorRepository.class);
    FilmRespository filmRespository = mock(FilmRespository.class);
    CategoryRepository categoryRepository = mock(CategoryRepository.class);
    FilmActorRepository filmActorRepository = mock(FilmActorRepository.class);
    CategoryFilmRepository categoryFilmRepository = mock(CategoryFilmRepository.class);
    StockRepository stockRepository = mock(StockRepository.class);
    StockController mockApp = new StockController(actorRepository, filmRespository, categoryRepository, filmActorRepository, categoryFilmRepository, stockRepository);

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
