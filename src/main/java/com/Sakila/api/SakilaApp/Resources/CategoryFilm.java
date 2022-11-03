package com.Sakila.api.SakilaApp.Resources;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "film_category")
public class CategoryFilm {

    @Id
    @Column(name = "film_id")
    int filmID;

    @Column(name = "category_id")
    int categoryID;

    @ManyToOne()
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    Film film;

    public CategoryFilm(int filmID, int categoryID, Film film){
        this.filmID = filmID;
        this.categoryID = categoryID;
        this.film = film;
    }

    public CategoryFilm() {

    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
