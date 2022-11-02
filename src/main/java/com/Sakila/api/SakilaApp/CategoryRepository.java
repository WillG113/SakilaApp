package com.Sakila.api.SakilaApp;

import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
