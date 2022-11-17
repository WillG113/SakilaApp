package com.movies.api.sakilaapp.cucumber;

import com.movies.api.sakilaapp.controllers.*;
import com.movies.api.sakilaapp.resources.*;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

public class OrderProcessStepDef {

    int customerID;
    int filmID;
    int initialStock;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private RentalRepository rentalRepo;

    @Autowired
    private StockRepository stockRepo;

    Rental rentReturn = new Rental();


    @Given("I have a film in my basket that is in stock")
    public void i_have_a_film_in_my_basket_that_is_in_stock() {

        filmID = 12;
        customerID = 1;
        Stock result = stockRepo.findById(12).orElse(null);
        if(result != null){
            initialStock = result.getStock();
        }

    }
    @When("I click the checkout button")
    public void i_click_the_checkout_button() {

        Rental newRental = new Rental(customerID, filmID);
        rentReturn = rentalRepo.save(newRental);
        stockRepo.lowerStock(filmID);

    }
    @Then("The order will be processed")
    public void the_order_will_be_processed() {


        rentalRepo.findById(rentReturn.getRentalID());
        Assertions.assertEquals(filmID, rentReturn.getFilmID());

    }
    @Then("the stock will be lowered for the given item")
    public void the_stock_will_be_lowered_for_the_given_item() {
        int afterStock = 0;
        Stock result = stockRepo.findById(12).orElse(null);
        if(result != null){
            afterStock = result.getStock();
        }
        Assertions.assertEquals(initialStock-1, afterStock);

    }



}
