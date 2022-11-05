package com.movies.api.sakilaapp.resources;

import javax.persistence.*;

@Entity
@Table(name = "film_stock")
public class Stock {

    @Id
    @Column(name = "film_id")
    int filmID;

    @Column(name = "stock")
    int filmStock;

    public Stock(int filmID, int filmStock) {
        this.filmID = filmID;
        this.filmStock = filmStock;
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
        return filmStock;
    }

    public void setStock(int stock) {
        this.filmStock = stock;
    }
}
