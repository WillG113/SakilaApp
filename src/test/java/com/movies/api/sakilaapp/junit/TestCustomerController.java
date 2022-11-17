package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.controllers.*;
import com.movies.api.sakilaapp.resources.*;
import io.cucumber.messages.types.*;
import org.junit.jupiter.api.*;

import java.lang.reflect.*;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

public class TestCustomerController {

    private final CustomerRepository customerRepository = mock(CustomerRepository.class);
    private final RentalRepository rentalRepository = mock(RentalRepository.class);

    private final CountryRepository countryRepository = mock(CountryRepository.class);

    private final CityRepository cityRepository = mock(CityRepository.class);

    private final AddressRepository addressRepository = mock(AddressRepository.class);

    private final StockRepository stockRepository = mock(StockRepository.class);

    CustomerController mockApp = new CustomerController(customerRepository, rentalRepository, countryRepository, addressRepository,cityRepository, stockRepository);


    Rental testRental = new Rental(1, 1);
    Rental testRental2 = new Rental(2,2);
    Iterable<Rental> allRentals = Arrays.asList(testRental, testRental2);
    List<Rental> allRentals2 = Arrays.asList(testRental, testRental2);

    @Test
    void testGetAllRentalsAPI() {

        when(rentalRepository.findAll()).thenReturn(allRentals2);
        Iterable<Rental> returnVal = mockApp.getAllRentalAPI();
        Assertions.assertEquals(allRentals, returnVal);

    }

    @Test
    void testGetAllRentalsByIDAPI() {

        when(rentalRepository.findOrdersByID(1)).thenReturn(allRentals2);
        Iterable<Rental> returnVal = mockApp.getAllRentalsByIDAPI(1);
        Assertions.assertEquals(allRentals, returnVal);

    }

    @Test
    void testGetAllReturnedRentalsByIDAPI() {

        when(rentalRepository.findReturnedOrdersByID(1)).thenReturn(allRentals2);
        Iterable<Rental> returnVal = mockApp.getAllReturnedRentalsByIDAPI(1);
        Assertions.assertEquals(allRentals, returnVal);

    }

    @Test
    void testPostRentalAPI() {


        when(rentalRepository.save(any(Rental.class))).thenReturn(testRental);
        Rental returnVal = mockApp.postRentalAPI(5, 5);
        Assertions.assertEquals(testRental, returnVal);

    }

    @Test
    void testReturnRentalAPI() {

        when(rentalRepository.findById(1)).thenReturn(Optional.ofNullable(testRental));
        Rental returnVal = mockApp.returnRentalAPI(1).orElse(testRental2);
        assert returnVal != null;
        Assertions.assertNotEquals(testRental.getReturnDate(), returnVal.getReturnDate());

    }

    @Test
    void getAllRentalsByEmailAPI() {

        Address testAddress = new Address("Test", "Test", 1, "Test", "Test");
        Customer testCustomer = new Customer("Fname", "Sname", "Test", 1, 1, testAddress);

        when(customerRepository.findByEmail("Test")).thenReturn(testCustomer);
        Customer returnVal = mockApp.getAllRentalsByEmailAPI("Test");
        Assertions.assertEquals(testCustomer, returnVal);

    }

    @Test
    void testPostCustomer() {

        Address testAddress = new Address("Test", "Test", 1, "Test", "Test");
        Customer testCustomer = new Customer("Fname", "Sname", "Test", 1, 1, testAddress);

        when(addressRepository.save(any(Address.class))).thenReturn(testAddress);
        testAddress.setAddressID(1);

        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);
        Customer returnVal = mockApp.postCustomer("Fname","Sname","Test", "Test", "Test", 1, "Test", "Test");
        Assertions.assertEquals(testCustomer, returnVal);

    }

    @Test
    void testGetAllCountry() {

        Country testCountry = new Country(1, "One");
        Country testCountry2 = new Country(2, "Two");
        List<Country> allCountry = Arrays.asList(testCountry, testCountry2);

        when(countryRepository.findAll()).thenReturn(allCountry);
        List<Country> returnVal = mockApp.getAllCountry();
        Assertions.assertEquals(allCountry, returnVal);

    }

    @Test
    void testGetCityByCountryID() {

        City testCity = new City(1, "One", 1, new Country(1,"One"));
        City testCity2 = new City(2, "Two", 2, new Country(1,"Two"));
        List<City> allCities = Arrays.asList(testCity, testCity2);

        when(cityRepository.findByCountryID(1)).thenReturn(allCities);
        List<City> returnVal = mockApp.getCityByCountryID(1);
        Assertions.assertEquals(allCities, returnVal);

    }


}
