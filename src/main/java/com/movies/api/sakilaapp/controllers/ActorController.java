package com.movies.api.sakilaapp.controllers;

import com.movies.api.sakilaapp.*;
import com.movies.api.sakilaapp.resources.*;
import org.json.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = {"http://localhost:3000/"})
public class ActorController extends SakilaAppApplication {

    private final ActorRepository actorRepository;
    private final FilmRespository filmRespository;
    private final FilmActorRepository filmActorRepository;

    public ActorController(ActorRepository actorRepository, FilmRespository filmRespository, FilmActorRepository filmActorRepository) {
        super();
        this.actorRepository = actorRepository;
        this.filmRespository = filmRespository;
        this.filmActorRepository = filmActorRepository;
    }

    @GetMapping("/api/actors")
    public Iterable<Actor> getAllActorsAPI() {
        return actorRepository.findAll();
    }

    @GetMapping("/actors")
    public ModelAndView getAllActors() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("actors");
        modelAndView.addObject("actorList", actorRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/actors/{id}")
    public ModelAndView getActor(@PathVariable int id) throws JSONException, InterruptedException {

        Actor input = actorRepository.findById(id).orElse(null);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("specificActor");
        modelAndView.addObject("actorList", input);
        modelAndView.addObject("filmList", filmRespository.findByActorID(id));

//		// REST REQUEST FUNCTION
        if(input != null) {
            String name = input.getActorFirstname() + " " + input.getActorLastname();
            modelAndView.addObject("image", getAi().fetchMethod(getAi().postMethod(name)));
        }

        return modelAndView;
    }

    @GetMapping("/api/actors/{id}")
    public Actor getActorAPI(@PathVariable int id) {
        return actorRepository.findById(id).orElseThrow(IndexOutOfBoundsException::new);
    }

    //Finds films based on a certain actor
    @GetMapping("/api/filmactors/{id}")
    public List<Film> getFilmActorAPI(@PathVariable int id) {
        return filmRespository.findByActorID(id);
    }

    //Finds films based on a certain actor
    @GetMapping("/api/actorfilms/{id}")
    public List<FilmActor> getActorFilmsAPI(@PathVariable int id) {
        return filmActorRepository.findByFilmID(id);
    }

    @PostMapping("/actors/{fname}+{sname}")
    public Actor addActor(@PathVariable String fname, @PathVariable String sname){
        Actor newActor = new Actor(fname, sname);
        return actorRepository.save(newActor);
    }

    @PutMapping("/actors/{id}+{fname}+{sname}")
    public Actor replaceActor(@PathVariable int id, @PathVariable String fname, @PathVariable String sname){
        Actor newActor = new Actor(fname, sname);

        return actorRepository.findById(id).map(actor -> {
            actor.setActorFirstname(newActor.getActorFirstname());
            actor.setActorLastname(newActor.getActorLastname());
            return actorRepository.save(actor);
        }).orElseGet(() -> {
            newActor.setActorID(id);
            return actorRepository.save(newActor);
        });
    }

    @DeleteMapping("/actors/{id}")
    public void deleteActor(@PathVariable int id) {
        actorRepository.deleteById(id);
    }

}
