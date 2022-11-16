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

    private final CountryRepository countryRepository;

    private final CityRepository cityRepository;

    private final AddressRepository addressRepository;

    public CustomerController(CustomerRepository customerRepository, RentalRepository rentalRepository, CountryRepository countryRepository, AddressRepository addressRepository, CityRepository cityRepository) {
        super();
        this.customerRepository = customerRepository;
        this.rentalRepository = rentalRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.addressRepository = addressRepository;
    }

    @GetMapping("/api/rentals")
    public Iterable<Rental> getAllRentalAPI() {
        return rentalRepository.findAll();
    }

    @GetMapping("/api/rentalsByCustomerID/{id}")
    public Iterable<Rental> getAllRentalsByIDAPI(@PathVariable int id) {
        return rentalRepository.findOrdersByID(id);
    }

    @GetMapping("/api/returnedRentalsByCustomerID/{id}")
    public Iterable<Rental> getAllReturnedRentalsByIDAPI(@PathVariable int id) {
        return rentalRepository.findReturnedOrdersByID(id);
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


    @PostMapping("/api/newCustomer/{fname}+{sname}+{email}+{address}+{district}+{city_id}+{postal_code}+{phone}")
    public Customer postCustomer(@PathVariable String fname, @PathVariable String sname, @PathVariable String email,
                                 @PathVariable String address, @PathVariable String district,
                                 @PathVariable int city_id, @PathVariable String postal_code,
                                 @PathVariable String phone) {
        Address newAddress = new Address(address, district, city_id, postal_code, phone);
        Address returnVal = addressRepository.save(newAddress);
        Customer newCustomer = new Customer(fname, sname, email, returnVal.getAddressID(), 1, returnVal);
        return customerRepository.save(newCustomer);
    }



    @GetMapping("/api/country")
    public List<Country> getAllCountry(){
        return countryRepository.findAll();
    }

    @GetMapping("/api/cityByCountryID/{id}")
    public List<City> getCityByCountryID(@PathVariable int id){
        return cityRepository.findByCountryID(id);
    }

}
