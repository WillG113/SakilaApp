package com.Sakila.api.sakilaapp.resources;

import org.springframework.data.jpa.repository.*;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
