package com.Sakila.api.SakilaApp;

import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface ActorRepository extends JpaRepository<Actor, Integer> {

//    @Query(value = "SELECT DISTINCT * FROM actor WHERE first_name = ?1 AND last_name = ?2", nativeQuery = true)
//    List<Actor> findByNames(String fname, String sname);

}
