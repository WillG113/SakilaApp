package com.movies.api.sakilaapp.resources;


import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CityRepository extends JpaRepository<City, Integer> {

    @Query(value = "SELECT * FROM city WHERE country_id = ?1", nativeQuery = true)
    public List<City> findByCountryID(int id);

}
