package com.movies.api.sakilaapp.resources;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    int rentalID;

    @Column(name = "rental_date")
    String rentalDate;

    @Column(name = "customer_id")
    int customerID;

    @Column(name = "film_id")
    int filmID;

    @Column(name = "return_date")
    String returnDate;

    @OneToOne()
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    Film film;

    public Rental(int customerID, int filmID) {
        this.customerID = customerID;
        this.filmID = filmID;
    }

    public Rental() {

    }

    public int getRentalID() {
        return rentalID;
    }

    public void setRentalID(int rentalID) {
        this.rentalID = rentalID;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
