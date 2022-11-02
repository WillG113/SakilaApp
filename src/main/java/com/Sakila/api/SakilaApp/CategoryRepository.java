package com.Sakila.api.SakilaApp;

import org.springframework.data.jpa.repository.*;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
