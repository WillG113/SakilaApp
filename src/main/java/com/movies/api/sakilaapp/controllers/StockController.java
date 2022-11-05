package com.movies.api.sakilaapp.controllers;

import com.movies.api.sakilaapp.*;
import com.movies.api.sakilaapp.resources.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController extends SakilaAppApplication {

    @Autowired
    private final StockRepository stockRepository;

    public StockController(ActorRepository actorRepository, FilmRespository filmRespository, CategoryRepository categoryRepository, FilmActorRepository filmActorRepository, CategoryFilmRepository categoryFilmRepository, StockRepository stockRepository) {
        super(actorRepository, filmRespository, categoryRepository, filmActorRepository, categoryFilmRepository, stockRepository);
        this.stockRepository = stockRepository;
    }

    @GetMapping("/api/lowerStock/{id}")
    public String lowerStockByID(@PathVariable int id) {
        stockRepository.lowerStock(id);
        return "stock updated";
    }

    @GetMapping("/api/increaseStock/{id}")
    public String increaseStockByID(@PathVariable int id) {
        stockRepository.increaseStock(id);
        return "stock updated";
    }
}
