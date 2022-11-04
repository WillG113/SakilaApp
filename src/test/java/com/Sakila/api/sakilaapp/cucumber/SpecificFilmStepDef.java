package com.Sakila.api.sakilaapp.cucumber;


import com.Sakila.api.sakilaapp.*;
import com.Sakila.api.sakilaapp.resources.*;
import io.cucumber.java.en.*;
import io.cucumber.spring.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;

@ScenarioScope
public class SpecificFilmStepDef {


    Film filmObj;

    @Autowired
    private FilmRespository filmRepo;
    @Autowired
    private ActorRepository actorRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private FilmActorRepository filmActorRepo;
    @Autowired
    private CategoryFilmRepository categoryFilmRepo;

    SakilaAppApplication mockApp = new SakilaAppApplication(actorRepo, filmRepo, categoryRepo, filmActorRepo, categoryFilmRepo);


    @Given("A user wants to find information about a film by it's ID")
    public void a_user_wants_to_find_information_about_a_film_by_it_s_id() {


    }
    @When("they search {int}")
    public void they_search(int string) {
        // Write code here that turns the phrase above into concrete actions

        //int test = Integer.getInteger(string);

        filmObj = filmRepo.findById(string).orElseThrow(() -> new IndexOutOfBoundsException());

        //throw new io.cucumber.java.PendingException();
    }
    @Then("the program responds with the film name {string}")
    public void the_program_responds_with_the_film_name(String string) {
        // Write code here that turns the phrase above into concrete actions

        Assertions.assertEquals(string, filmObj.getTitle());

        //throw new io.cucumber.java.PendingException();
    }

}
