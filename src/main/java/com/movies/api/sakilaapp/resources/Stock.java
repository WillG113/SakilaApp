package com.movies.api.sakilaapp.resources;

import javax.persistence.*;

@Entity
@Table(name = "film_stock")
public class Stock {

    @Id
    @Column(name = "film_id")
    int filmID;

    @Column(name = "stock")
    int stock;

    public Stock(int filmID, int stock) {
        this.filmID = filmID;
        this.stock = stock;
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
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
