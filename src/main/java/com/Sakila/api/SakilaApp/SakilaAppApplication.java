package com.Sakila.api.SakilaApp;

import org.json.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;
import org.springframework.web.servlet.*;

import javax.swing.text.html.*;
import java.util.*;
import java.util.concurrent.*;


@SpringBootApplication
@RestController
@RequestMapping("/")
@CrossOrigin
public class SakilaAppApplication {

	@Autowired
	private ActorRepository actorRepository;
	private FilmRespository filmRespository;
	private CategoryRepository categoryRepository;
	private FilmActorRepository filmActorRepository;
	private CategoryFilmRepository categoryFilmRepository;
	private AiGeneration ai;

	public SakilaAppApplication(ActorRepository actorRepository, FilmRespository filmRespository, CategoryRepository categoryRepository, FilmActorRepository filmActorRepository, CategoryFilmRepository categoryFilmRepository, AiGeneration ai){
		this.actorRepository = actorRepository;
		this.filmRespository = filmRespository;
		this.categoryRepository = categoryRepository;
		this.filmActorRepository = filmActorRepository;
		this.categoryFilmRepository = categoryFilmRepository;
		this.ai = ai;
	}


	public static void main(String[] args) {
		SpringApplication.run(SakilaAppApplication.class, args);
	}

	@GetMapping("/api/actors")
	Iterable<Actor> getAllActorsAPI() {
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
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("specificActor");
		modelAndView.addObject("actorList", actorRepository.findById(id).orElseThrow(() -> new ResourceAccessException("Actor not found")));
		modelAndView.addObject("filmList", filmRespository.findByActorID(id));

//		// REST REQUEST FUNCTION
		Actor input = actorRepository.findById(id).orElse(input = null);
		String name = input.getActorFirstname() + " " + input.getActorLastname();
		if(input != null) {
			modelAndView.addObject("image", ai.fetchMethod(ai.postMethod(name)));
		}

		return modelAndView;
	}

	@GetMapping("/api/actors/{id}")
	Actor getActorAPI(@PathVariable int id) {
		return actorRepository.findById(id).orElseThrow(() -> new IndexOutOfBoundsException());
	}

	//Finds films based on a certain actor
	@GetMapping("/api/filmactors/{id}")
	List<Film> getFilmActorAPI(@PathVariable int id) {
		return filmRespository.findByActorID(id);
	}

	//Finds films based on a certain actor
	@GetMapping("/api/actorfilms/{id}")
	List<FilmActor> getActorFilmsAPI(@PathVariable int id) {
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

	// ------------------------------------------------------


	@GetMapping("/api/films")
	Iterable<Film> getAllFilmsAPI() {
		return filmRespository.findAll();
	}

	@GetMapping("/films")
	public ModelAndView getAllFilms() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("films");
		modelAndView.addObject("filmList", filmRespository.findAll());
		return modelAndView;
	}

	@GetMapping("/api/films/{id}")
	Film getFilmAPI(@PathVariable int id) {
		return filmRespository.findById(id).orElseThrow(IndexOutOfBoundsException::new);
	}

	@GetMapping("/films/{id}")
	public ModelAndView getFilm(@PathVariable int id) throws JSONException, InterruptedException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("specificFilm");
		modelAndView.addObject("film", filmRespository.findById(id).orElseThrow(() -> new IndexOutOfBoundsException()));
		List<CategoryFilm> category = categoryFilmRepository.findbyFilmID(id);
		String filmCategory = categoryFilmRepository.findCategoryByFilmID(id);
		modelAndView.addObject("suggestedFilms", categoryFilmRepository.findByCategoryID(category.get(0).categoryID));
		modelAndView.addObject("category", filmCategory);
		modelAndView.addObject("actorList", filmActorRepository.findByFilmID(id));
		System.out.println(filmActorRepository.findByFilmID(id));

//		// REST REQUEST FUNCTION
		Film input = filmRespository.findById(id).orElse(input = null);

		if(input != null) {
			//fetchMethod(postMethod(input.getDesc()));
			modelAndView.addObject("image", ai.fetchMethod(ai.postMethod(input.getDesc())));
		}
		return modelAndView;
	}

	@PostMapping("/films/{title}+{desc}+{length}")
	public Film addFilm(@PathVariable String title, @PathVariable String desc, @PathVariable String releaseYear, @PathVariable int rentDuration, @PathVariable double rentRate, @PathVariable int length, @PathVariable double replacementCost, @PathVariable String rating){
		Film newFilm = new Film(title, desc, releaseYear, rentDuration, rentRate, length, replacementCost, rating);
		return filmRespository.save(newFilm);
	}

	@PutMapping("/films/{id}+{title}+{desc}+{length}")
	public Film replaceFilm(@PathVariable int id, @PathVariable String title, @PathVariable String desc, @PathVariable String releaseYear, @PathVariable int rentDuration, @PathVariable double rentRate, @PathVariable int length, @PathVariable double replacementCost, @PathVariable String rating){
		Film newFilm = new Film(title, desc, releaseYear, rentDuration, rentRate, length, replacementCost, rating);

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

	// ------------------------------------------------------

	@GetMapping("/api/categories")
	Iterable<Category> getAllCategoriesAPI() {
		return categoryRepository.findAll();
	}

	@GetMapping("/api/filmcategories")
	Iterable<CategoryFilm> getAllFilmCategoriesAPI() {
		return categoryFilmRepository.findByCategoryID(3);
	}

	// --------------------------------- OTHER METHODS ----


}
