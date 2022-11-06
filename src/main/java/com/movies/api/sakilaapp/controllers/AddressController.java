package com.movies.api.sakilaapp.controllers;

import com.movies.api.sakilaapp.*;
import com.movies.api.sakilaapp.resources.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class AddressController extends SakilaAppApplication {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final RentalRepository rentalRepository;

    public AddressController(AddressRepository addressRepository, CustomerRepository customerRepository, RentalRepository rentalRepository) {
        super();
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
        this.rentalRepository = rentalRepository;
    }

    @GetMapping("/api/address")
    public Iterable<Address> getAllAddressesAPI() {
        return addressRepository.findAll();
    }

    @GetMapping("/api/customer")
    public Iterable<Customer> getAllCustomersAPI() {
        return customerRepository.findAll();
    }

    @GetMapping("/api/rental")
    public Iterable<Rental> getAllRentalsAPI() {
        return rentalRepository.findOrders();
    }

    @GetMapping("/api/rental/{id}")
    public Iterable<Rental> getAllRentalsAPI(@PathVariable int id) {
        return rentalRepository.findOrdersByID(id);
    }

}
