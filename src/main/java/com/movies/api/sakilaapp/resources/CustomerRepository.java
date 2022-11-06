package com.movies.api.sakilaapp.resources;

import org.springframework.data.jpa.repository.*;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
