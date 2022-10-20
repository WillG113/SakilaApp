package com.Sakila.api.SakilaApp;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int addressID;

    @Column(name = "address")
    String address;

    @Column(name = "district")
    String district;

    @Column(name = "postal_code")
    String postcode;

    @Column(name = "city_id")
    String cityID;

    public Address(String address, String district, String postcode, String cityID) {
        this.address = address;
        this.district = district;
        this.postcode = postcode;
        this.cityID = cityID;
    }

    public Address() {

    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }
}
