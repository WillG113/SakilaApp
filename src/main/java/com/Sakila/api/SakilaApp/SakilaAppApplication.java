package com.Sakila.api.SakilaApp;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
@RequestMapping("/index")
@CrossOrigin
public class SakilaAppApplication {

	@Autowired
	private ActorRepository actorRepository;
	private FilmRespository filmRespository;

	public SakilaAppApplication(ActorRepository actorRepository, FilmRespository filmRespository){
		this.actorRepository = actorRepository;
		this.filmRespository = filmRespository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SakilaAppApplication.class, args);
	}

	@GetMapping("/actors")
	Iterable<Actor> getAllActors() {
		return actorRepository.findAll();
	}

	@GetMapping("/actors/{id}")
	Actor getActor(@PathVariable int id) {
		return actorRepository.findById(id).orElseThrow(() -> new IndexOutOfBoundsException());
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

	@GetMapping("/films")
	Iterable<Film> getAllFilms() {
		return filmRespository.findAll();
	}

	@GetMapping("/films/{id}")
	Film getFilm(@PathVariable int id) {
		return filmRespository.findById(id).orElseThrow(() -> new IndexOutOfBoundsException());
	}

	@PostMapping("/films/{title}+{desc}+{length}")
	public Film addFilm(@PathVariable String title, @PathVariable String desc, @PathVariable int length){
		Film newFilm = new Film(title, desc, length);
		return filmRespository.save(newFilm);
	}

	@PutMapping("/films/{id}+{title}+{desc}+{length}")
	public Film replaceFilm(@PathVariable int id, @PathVariable String title, @PathVariable String desc, @PathVariable int length){
		Film newFilm = new Film(title, desc, length);

		return filmRespository.findById(id).map(actor -> {
			newFilm.setTitle(newFilm.getTitle());
			newFilm.setDesc((newFilm.getDesc()));
			newFilm.setLength(newFilm.getLength());
			return filmRespository.save(actor);
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
