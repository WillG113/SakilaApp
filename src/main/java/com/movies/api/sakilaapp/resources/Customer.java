package com.movies.api.sakilaapp.resources;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_id")
    int customerID;

    @Column(name = "first_name")
    String fname;

    @Column(name = "last_name")
    String lname;

    @Column(name = "email")
    String email;

    @Column(name = "address_id")
    int addressID;

    @Column(name = "active")
    int userActive;

    @OneToOne()
    @JoinColumn(name = "address_id", updatable = false, insertable = false)
    @Fetch(FetchMode.SELECT)
    Address address;

    public Customer(int customerID, String fname, String lname, String email, int addressID, int userActive, Address address) {
        this.customerID = customerID;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.addressID = addressID;
        this.userActive = userActive;
        this.address = address;
    }

    public Customer() {

    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public int getUserActive() {
        return userActive;
    }

    public void setUserActive(int userActive) {
        this.userActive = userActive;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
