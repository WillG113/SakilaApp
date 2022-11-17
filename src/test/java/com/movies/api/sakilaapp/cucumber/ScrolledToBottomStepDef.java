package com.movies.api.sakilaapp.cucumber;

import com.movies.api.sakilaapp.resources.*;
import io.cucumber.java.en.*;
import io.cucumber.spring.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

public class ScrolledToBottomStepDef {

    ArrayList<Film> filmList = new ArrayList<Film>();

    @Autowired
    private FilmRespository filmRepo;


    @Given("A user had loaded the index page of the application")
    public void a_user_had_loaded_the_index_page_of_the_application() {
        // Write code here that turns the phrase above into concrete actions

        filmRepo.findAllLimit(0, 10).forEach(film -> {
            filmList.add(film);
        });

        //throw new io.cucumber.java.PendingException();
    }
    @When("the user reaches the bottom of the page")
    public void the_user_reaches_the_bottom_of_the_page() {
        // Write code here that turns the phrase above into concrete actions

        filmRepo.findAllLimit(10, 10).forEach(film -> {
            filmList.add(film);
        });

        //throw new io.cucumber.java.PendingException();
    }
    @Then("additional values will be loaded and added to the page")
    public void additional_values_will_be_loaded_and_added_to_the_page() {
        // Write code here that turns the phrase above into concrete actions

        Assertions.assertEquals(20, filmList.size());
        //throw new io.cucumber.java.PendingException();
    }



}
