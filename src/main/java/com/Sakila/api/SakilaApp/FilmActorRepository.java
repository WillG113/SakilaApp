package com.Sakila.api.SakilaApp;

import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface FilmActorRepository extends JpaRepository<FilmActor, Integer> {

    @Query(value = "SELECT DISTINCT a.actor_id, fa.film_id, a.first_name, a.last_name FROM film_actor fa RIGHT JOIN actor a ON fa.actor_id = a.actor_id WHERE fa.film_id = ?1", nativeQuery = true)
    List<FilmActor> findByFilmID(int id);


}
