package com.movies.api.sakilaapp.resources;

import org.springframework.data.jpa.repository.*;

import javax.transaction.*;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE film_stock SET stock = stock - 1 WHERE film_id = ?1", nativeQuery = true)
    void lowerStock(int filmID);

    @Transactional
    @Modifying
    @Query(value = "UPDATE film_stock SET stock = stock + 1 WHERE film_id = ?1", nativeQuery = true)
    void increaseStock(int filmID);

}
