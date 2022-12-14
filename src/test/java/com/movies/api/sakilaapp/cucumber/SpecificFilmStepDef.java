package com.movies.api.sakilaapp.cucumber;


import com.movies.api.sakilaapp.*;
import com.movies.api.sakilaapp.controllers.*;
import com.movies.api.sakilaapp.resources.*;
import io.cucumber.java.en.*;
import io.cucumber.spring.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ScenarioScope
public class SpecificFilmStepDef {

    Film filmObj;

    @Autowired
    private FilmRespository filmRepo;

    @Given("A user wants to find information about a film by it's ID")
    public void a_user_wants_to_find_information_about_a_film_by_it_s_id() {
    }
    @When("they search {int}")
    public void they_search(int string) {


        filmObj = filmRepo.findById(string).orElseThrow(() -> new IndexOutOfBoundsException());

    }
    @Then("the program responds with the film name {string}")
    public void the_program_responds_with_the_film_name(String string) {

        Assertions.assertEquals(string, filmObj.getTitle());

    }

}
