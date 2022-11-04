package com.Sakila.api.sakilaapp.resources;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "film_actor")
public class FilmActor {

    @Column(name = "film_id")
    int filmID;

    @Id
    @Column(name = "actor_id")
    int actorID;

    @ManyToOne()
    @JoinColumn(name = "actor_id", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    Actor actor;

    public FilmActor(int filmID, int actorID, Actor actor){
        this.filmID = filmID;
        this.actorID = actorID;
        this.actor = actor;
    }
    public FilmActor() {

    }


    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getActorID() {
        return actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}


