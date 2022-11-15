package com.movies.api.sakilaapp.resources;

import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    @Query(value = "SELECT * FROM rental LIMIT 500", nativeQuery = true)
    List<Rental> findOrders();

    @Query(value = "SELECT * FROM rental WHERE customer_id = ?1 ORDER BY rental_date DESC", nativeQuery = true)
    List<Rental> findOrdersByID(int customerID);

}
