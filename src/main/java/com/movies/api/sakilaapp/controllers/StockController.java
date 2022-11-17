package com.movies.api.sakilaapp.controllers;

import com.movies.api.sakilaapp.*;
import com.movies.api.sakilaapp.resources.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000/", "http://192.168.1.250:3000/"})
public class StockController extends SakilaAppApplication {

    @Autowired
    private final StockRepository stockRepository;

    public StockController(StockRepository stockRepository) {
        super();
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

    @GetMapping("/api/checkStock/{id}")
    public Stock checkStockAPI(@PathVariable int id){
        return stockRepository.checkStock(id);
    }
}
