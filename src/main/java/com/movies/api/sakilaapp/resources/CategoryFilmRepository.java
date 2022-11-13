package com.movies.api.sakilaapp.resources;

import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CategoryFilmRepository extends JpaRepository<CategoryFilm, Integer> {

    @Query(value = "SELECT DISTINCT f.film_id, f.title, f.description, c.category_id, ca.name FROM film f " +
            "RIGHT JOIN film_category c " +
            "ON f.film_id = c.film_id " +
            "RIGHT JOIN category ca " +
            "ON ca.category_id = c.category_id " +
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
