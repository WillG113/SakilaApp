package com.Sakila.api.SakilaApp.Resources;

import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface FilmRespository extends JpaRepository<Film, Integer> {

    @Query(value = "SELECT DISTINCT f.film_id, f.title, f.description, f.release_year, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating FROM film_actor fa RIGHT JOIN film f ON f.film_id = fa.film_id WHERE fa.actor_id = ?1", nativeQuery = true)
    List<Film> findByActorID(int id);

}
