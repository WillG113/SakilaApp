package com.movies.api.sakilaapp.resources;

import javax.persistence.*;

@Entity
@Table(name = "film_stock")
public class Stock {

    @Id
    @Column(name = "film_id")
    int filmID;

    @Column(name = "stock")
    int film_stock;

    public Stock(int filmID, int film_stock) {
        this.filmID = filmID;
        this.film_stock = film_stock;
    }

    public Stock() {

    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getStock() {
        return film_stock;
    }

    public void setStock(int stock) {
        this.film_stock = stock;
    }
}
