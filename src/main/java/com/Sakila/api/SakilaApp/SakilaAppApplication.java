package com.Sakila.api.SakilaApp;

import org.json.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;
import org.springframework.web.servlet.*;

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

	public SakilaAppApplication(ActorRepository actorRepository, FilmRespository filmRespository, CategoryRepository categoryRepository, FilmActorRepository filmActorRepository){
		this.actorRepository = actorRepository;
		this.filmRespository = filmRespository;
		this.categoryRepository = categoryRepository;
		this.filmActorRepository = filmActorRepository;
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
			modelAndView.addObject("image", fetchMethod(postMethod(name)));
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
		return filmRespository.findById(id).orElseThrow(() -> new IndexOutOfBoundsException());
	}

	@GetMapping("/films/{id}")
	public ModelAndView getFilm(@PathVariable int id) throws JSONException, InterruptedException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("specificFilm");
		modelAndView.addObject("film", filmRespository.findById(id).orElseThrow(() -> new IndexOutOfBoundsException()));
		modelAndView.addObject("actorList", filmActorRepository.findByFilmID(id));
		System.out.println(filmActorRepository.findByFilmID(id));

//		// REST REQUEST FUNCTION
		Film input = filmRespository.findById(id).orElse(input = null);

		if(input != null) {
			//fetchMethod(postMethod(input.getDesc()));
			modelAndView.addObject("image", fetchMethod(postMethod(input.getDesc())));
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

	// --------------------------------- OTHER METHODS ----

	public String fetchMethod(String URL) throws JSONException, InterruptedException {
		RestTemplate restTemplate= new RestTemplate();
		JSONObject obj;

		do {
			TimeUnit.MILLISECONDS.sleep(250);

			ResponseEntity<String> response = restTemplate.getForEntity("https://stablehorde.net/api/v2/generate/check/" + URL, String.class);
			String test = response.getBody();

			obj = new JSONObject(response.getBody());
			System.out.println("trying - queue position: " + obj.getString("queue_position"));
		}

		while(!obj.getString("finished").equals("1"));

		ResponseEntity<String> response = restTemplate.getForEntity("https://stablehorde.net/api/v2/generate/status/" + URL, String.class);

		obj = new JSONObject(response.getBody());
		JSONArray test = obj.getJSONArray("generations");
		String imageSource = test.getJSONObject(0).getString("img");
		System.out.println("done");
		return imageSource;
	}


	public String postMethod(String title) throws JSONException {

		RestTemplate restTemplate= new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		String test = "{\"prompt\": \"" + title + "\"}";
		JSONObject obj = new JSONObject(test);

		//headers.add("apikey", "ob3dJ5IV9yr2bDppOIpeRw");
		headers.add("apikey", "tfgQCv8pHWSRiv8PRP94VA");
		headers.add("accept", "application/json");
		headers.add("Content-Type", "application/json");

		HttpEntity<String> entity = new HttpEntity<>(obj.toString(), headers);

		String URL = "https://stablehorde.net/api/v2/generate/async";
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

		String responseTest = response.getBody();
		System.out.println(responseTest);

		JSONObject objTest = new JSONObject(response.getBody());
		String links = objTest.getString("id");

		return links;

	}

}
