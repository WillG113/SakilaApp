package com.movies.api.sakilaapp.controllers;



import com.movies.api.sakilaapp.*;
import com.movies.api.sakilaapp.resources.*;
import org.json.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@RestController
@EnableAutoConfiguration
@CrossOrigin()
public class FilmController extends SakilaAppApplication {

    @Autowired
    private final FilmRespository filmRespository;
    @Autowired
    private final FilmActorRepository filmActorRepository;
    @Autowired
    private final CategoryFilmRepository categoryFilmRepository;


    public FilmController(FilmRespository filmRespository, FilmActorRepository filmActorRepository, CategoryFilmRepository categoryFilmRepository) {
        super();
        this.filmRespository = filmRespository;
        this.filmActorRepository = filmActorRepository;
        this.categoryFilmRepository = categoryFilmRepository;
    }

    @GetMapping("/api/films")
    public Iterable<Film> getAllFilmsAPI() {
        return filmRespository.findAll();
    }

    @GetMapping("/api/filmsLimit/{start}+{end}")
    public Iterable<Film> getAllFilmsLimitAPI(@PathVariable int start, @PathVariable int end) {
        return filmRespository.findAllLimit(start, end);
    }

    @GetMapping("/api/searchFilms/{query}")
    public Iterable<Film> getFilmAPI(@PathVariable String query) {
        return filmRespository.findByQuery(query);
    }

    @GetMapping("/films")
    public ModelAndView getAllFilms() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        modelAndView.addObject("filmList", filmRespository.findAll());
        return modelAndView;
    }

    @GetMapping("/api/films/{id}")
    public Film getFilmAPI(@PathVariable int id) {
        return filmRespository.findById(id).orElseThrow(IndexOutOfBoundsException::new);
    }

    @GetMapping("/api/filmByCategory/{id}")
    public List<Film> getFilmsByCategoryIDAPI(@PathVariable int id) {
        return filmRespository.findByCategoryID(id);
    }

    @GetMapping("/films/{id}")
    public ModelAndView getFilm(@PathVariable int id) throws JSONException, InterruptedException {

        filmRespository.updateScore(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("specificFilm");
        modelAndView.addObject("film", filmRespository.findById(id).orElseThrow(IndexOutOfBoundsException::new));
        List<CategoryFilm> category = categoryFilmRepository.findbyFilmID(id);
        String filmCategory = categoryFilmRepository.findCategoryByFilmID(id);
        modelAndView.addObject("suggestedFilms", categoryFilmRepository.findByCategoryID(category.get(0).getCategoryID()));
        modelAndView.addObject("category", filmCategory);
        modelAndView.addObject("actorList", filmActorRepository.findByFilmID(id));

//		// REST REQUEST FUNCTION
        Film input = filmRespository.findById(id).orElse(null);

        if(input != null) {
            modelAndView.addObject("image", getAi().fetchMethod(getAi().postMethod(input.getDesc())));
        }
        return modelAndView;
    }

    @GetMapping("/api/rfilms/{id}")
    public List<Film> getRFilmAPI(@PathVariable int id) {
        return filmRespository.findFive(id);
    }

    @PostMapping("/films/{title}+{desc}+{length}")
    public Film addFilm(@PathVariable String title, @PathVariable String desc, @PathVariable String releaseYear, @PathVariable int rentDuration, @PathVariable double rentRate, @PathVariable int length, @PathVariable double replacementCost, @PathVariable String rating){
        Film newFilm = new Film(title, desc, releaseYear, rentDuration, rentRate, length, replacementCost, rating, 0);
        return filmRespository.save(newFilm);
    }

    @PutMapping("/films/{id}+{title}+{desc}+{length}")
    public Film replaceFilm(@PathVariable int id, @PathVariable String title, @PathVariable String desc, @PathVariable String releaseYear, @PathVariable int rentDuration, @PathVariable double rentRate, @PathVariable int length, @PathVariable double replacementCost, @PathVariable String rating){
        Film newFilm = new Film(title, desc, releaseYear, rentDuration, rentRate, length, replacementCost, rating, 0);

        return filmRespository.findById(id).map(film -> {
            film.setTitle(newFilm.getTitle());
            film.setDesc((newFilm.getDesc()));
            film.setLength(newFilm.getLength());
            return filmRespository.save(film);
        }).orElseGet(() -> {
            newFilm.setFilmID(id);
            return filmRespository.save(newFilm);
        });
    }

    @DeleteMapping("/films/{id}")
    public void deleteFilm(@PathVariable int id) {
        filmRespository.deleteById(id);
    }

}
