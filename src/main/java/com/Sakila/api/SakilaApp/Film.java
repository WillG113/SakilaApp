package com.Sakila.api.SakilaApp;

import javax.persistence.*;

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

    @Column(name = "length")
    int length;


    //Constructor
    public Film(String title, String desc, int length) {
        this.title = title;
        this.desc = desc;
        this.length = length;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
