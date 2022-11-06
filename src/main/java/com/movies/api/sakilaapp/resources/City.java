package com.movies.api.sakilaapp.resources;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City {

    @Id
    @Column(name = "city_id")
    int cityID;

    @Column(name = "city")
    String cityName;

    @Column(name = "country_id")
    int countryID;

    @OneToOne()
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    Country country;

    public City(int cityID, String cityName, int countryID, Country country) {
        this.cityID = cityID;
        this.cityName = cityName;
        this.countryID = countryID;
        this.country = country;
    }

    public City() {

    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
