package com.movies.api.sakilaapp.resources;

import org.hibernate.annotations.*;
import org.springframework.beans.factory.annotation.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="film")
public class Film {

    //Attributes
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int filmID;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String desc;

    @Column(name = "release_year")
    String releaseYear;

    @Column(name = "rental_duration")
    int rentDuration;

    @Column(name = "rental_rate")
    double rentRate;

    @Column(name = "length")
    int length;

    @Column(name = "replacement_cost")
    double replacementCost;

    @Column(name = "rating")
    String rating;

    @Column(name = "score")
    int score;


    @OneToOne()
    @JoinColumn(name = "film_id", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    Stock stock;

    //Constructor
    @Autowired
    public Film(String title, String desc, String releaseYear, int rentDuration, double rentRate, int length, double replacementCost, String rating, int score) {
        this.title = title;
        this.desc = desc;
        this.releaseYear = releaseYear;
        this.rentDuration = rentDuration;
        this.rentRate = rentRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.score = score;
        this.stock = new Stock(0, 0);
    }

    public Film() {

    }

    //Methods

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getRentDuration() {
        return rentDuration;
    }

    public void setRentDuration(int rentDuration) {
        this.rentDuration = rentDuration;
    }

    public double getRentRate() {
        return rentRate;
    }

    public void setRentRate(double rentRate) {
        this.rentRate = rentRate;
    }

    public double getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(double replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
