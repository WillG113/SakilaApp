package com.movies.api.sakilaapp.cucumber;

import com.movies.api.sakilaapp.*;
import com.movies.api.sakilaapp.resources.*;
import io.cucumber.java.en.*;
import io.cucumber.spring.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;

@ScenarioScope
public class SpecificActorsStepDef {

    Actor actorObj;

    @Autowired
    private ActorRepository actorRepo;


    @Given("A user wants to find information about an actor by their ID")
    public void a_user_wants_to_find_information_about_an_actor_by_their_id() {
    }
    @When("they search the {int}")
    public void they_search_the(Integer int1) {

        actorObj = actorRepo.findById(int1).orElseThrow(() -> new IndexOutOfBoundsException());

    }
    @Then("the program responds with the actor firstname {string}")
    public void the_program_responds_with_the_actor_firstname(String string) {

        Assertions.assertEquals(string, actorObj.getActorFirstname());
    }

}
