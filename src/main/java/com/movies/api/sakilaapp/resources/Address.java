package com.movies.api.sakilaapp.resources;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "address_id")
    int addressID;

    @Column(name = "address")
    String addressLine;

    @Column(name = "district")
    String districtName;

    @Column(name = "city_id")
    int cityID;

    @Column(name = "postal_code")
    String postcode;

    @Column(name = "phone")
    String phoneNo;

    @OneToOne()
    @JoinColumn(name = "city_id", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    City city;

    public Address(int addressID, String addressLine, String districtName, int cityID, String postcode, String phoneNo, City city) {
        this.addressID = addressID;
        this.addressLine = addressLine;
        this.districtName = districtName;
        this.cityID = cityID;
        this.postcode = postcode;
        this.phoneNo = phoneNo;
        this.city = city;
    }

    public Address() {

    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
