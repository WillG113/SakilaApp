package com.movies.api.sakilaapp.resources;

import org.springframework.data.jpa.repository.*;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "SELECT * FROM customer WHERE email = ?1", nativeQuery = true)
    Customer findByEmail(String email);


}
