package com.movies.api.sakilaapp.resources;

import org.springframework.data.jpa.repository.*;

import javax.transaction.*;
import java.util.*;

public interface FilmRespository extends JpaRepository<Film, Integer> {

    @Query(value = "SELECT DISTINCT f.film_id, f.title, f.description, f.release_year, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.score FROM film_actor fa RIGHT JOIN film f ON f.film_id = fa.film_id WHERE fa.actor_id = ?1", nativeQuery = true)
    List<Film> findByActorID(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE film SET score = score + 1 WHERE film_id = ?1", nativeQuery = true)
    void updateScore(int id);

    @Query(value = "SELECT DISTINCT * FROM film WHERE film_id != ?1 ORDER BY rand() LIMIT 5", nativeQuery = true)
    List<Film> findFive(int id);

    @Query(value = "SELECT * FROM film LIMIT ?1, ?2", nativeQuery = true)
    Iterable<Film> findAllLimit(int start, int end);

    @Query(value = "SELECT * FROM film WHERE title LIKE %?1% OR description LIKE %?1% LIMIT ?2, ?3", nativeQuery = true)
    Iterable<Film> findByQuery(String query, int start, int end);

    @Query(value = "SELECT DISTINCT * FROM film f " +
            "RIGHT JOIN film_category c " +
            "ON f.film_id = c.film_id " +
            "RIGHT JOIN category ca " +
            "ON ca.category_id = c.category_id " +
            "WHERE c.category_id = ?1 " +
            "ORDER BY rand() limit 5", nativeQuery = true)
    List<Film> findByCategoryID(int id);

}
