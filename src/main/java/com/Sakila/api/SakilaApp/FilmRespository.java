package com.Sakila.api.SakilaApp;

import org.springframework.data.jpa.repository.*;

public interface FilmRespository extends JpaRepository<Film, Integer> {
}
