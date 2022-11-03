package com.Sakila.api.SakilaApp.CucumberTests;

import com.Sakila.api.SakilaApp.*;
import com.Sakila.api.SakilaApp.Resources.*;
import io.cucumber.java.en.*;
import io.cucumber.spring.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;

@ScenarioScope
public class SpecificActorsStepDef {

    Actor actorObj;

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


    @Given("A user wants to find information about an actor by their ID")
    public void a_user_wants_to_find_information_about_an_actor_by_their_id() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
    }
    @When("they search the {int}")
    public void they_search_the(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();

        actorObj = actorRepo.findById(int1).orElseThrow(() -> new IndexOutOfBoundsException());

    }
    @Then("the program responds with the actor firstname {string}")
    public void the_program_responds_with_the_actor_firstname(String string) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();

        Assertions.assertEquals(string, actorObj.getActorFirstname());
    }

}
