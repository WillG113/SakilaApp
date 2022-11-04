package com.Sakila.api.sakilaapp.resources;

import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CategoryFilmRepository extends JpaRepository<CategoryFilm, Integer> {

    @Query(value = "SELECT DISTINCT * FROM film_category c " +
            "RIGHT JOIN film f " +
            "ON f.film_ID = c.film_id " +
            "WHERE c.category_id = ?1 " +
            "ORDER BY rand() limit 5", nativeQuery = true)
    List<CategoryFilm> findByCategoryID(int id);

    @Query(value = "SELECT DISTINCT category_id, film_id FROM film_category " +
            "WHERE film_id = ?1", nativeQuery = true)
    List<CategoryFilm> findbyFilmID(int id);

    @Query(value = "SELECT c.name FROM category c " +
            "RIGHT JOIN film_category fc ON c.category_id = fc.category_id " +
            "WHERE film_id = ?1", nativeQuery = true)
    String findCategoryByFilmID(int id);
}
