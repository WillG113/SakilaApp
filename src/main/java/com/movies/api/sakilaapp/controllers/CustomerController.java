package com.movies.api.sakilaapp.controllers;

import com.movies.api.sakilaapp.resources.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

@RestController
@EnableAutoConfiguration
@CrossOrigin()
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final RentalRepository rentalRepository;

    public CustomerController(CustomerRepository customerRepository, RentalRepository rentalRepository) {
        super();
        this.customerRepository = customerRepository;
        this.rentalRepository = rentalRepository;
    }

    @GetMapping("/api/rentals")
    public Iterable<Rental> getAllRentalAPI() {
        return rentalRepository.findAll();
    }

    @GetMapping("/api/rentalsByCustomerID/{id}")
    public Iterable<Rental> getAllRentalsByIDAPI(@PathVariable int id) {
        return rentalRepository.findOrdersByID(id);
    }


    @PostMapping("/api/rentals/{filmID}+{customerID}")
    public Rental postRentalAPI(@PathVariable int filmID, @PathVariable int customerID) {
        Rental newRental = new Rental(customerID, filmID);
        return rentalRepository.save(newRental);
    }

    @GetMapping("/api/rentalReturn/{id}")
    public Optional<Rental> returnRentalAPI(@PathVariable int id) {

        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return rentalRepository.findById(id).map(rental -> {
            rental.setReturnDate(sdf.format(dt));
            return rentalRepository.save(rental);
        });

    }

    @GetMapping("/api/customerIDbyEmail/{email}")
    public Customer getAllRentalsByIDAPI(@PathVariable String email) {
        return customerRepository.findByEmail(email);
    }


}
