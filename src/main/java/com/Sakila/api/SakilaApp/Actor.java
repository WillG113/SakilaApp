package com.Sakila.api.SakilaApp;


import javax.persistence.*;

@Entity
@Table(name="actor")
public class Actor {

    //Attrbutes
    @Id
    @Column(name = "actor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int actorID;

    @Column(name = "first_name")
    String actorFirstname;

    @Column(name = "last_name")
    String actorLastname;

    //Constructor
    public Actor(String actorFirstname, String actorLastname) {
        this.actorFirstname = actorFirstname;
        this.actorLastname = actorLastname;
    }

    public Actor(){

    }

    //Methods

    public int getActorID() {
        return actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    public String getActorFirstname() {
        return actorFirstname;
    }

    public void setActorFirstname(String actorFirstname) {
        this.actorFirstname = actorFirstname;
    }

    public String getActorLastname() {
        return actorLastname;
    }

    public void setActorLastname(String actorLastname) {
        this.actorLastname = actorLastname;
    }
}
