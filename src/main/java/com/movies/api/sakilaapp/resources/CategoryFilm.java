package com.movies.api.sakilaapp.resources;

import com.fasterxml.jackson.annotation.*;
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

    @OneToOne()
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    Category categoryName;

    @JsonIgnore()
    @OneToOne()
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    Film film;

    public CategoryFilm(int filmID, int categoryID){
        this.filmID = filmID;
        this.categoryID = categoryID;
        this.categoryName = new Category(1, "Test");
        this.film = new Film();
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

    public Category getCategory() {
        return categoryName;
    }

    public void setCategory(Category category) {
        this.categoryName = category;
    }


    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
