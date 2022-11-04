package com.Sakila.api.sakilaapp;

import com.Sakila.api.sakilaapp.resources.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/")
@CrossOrigin
public class SakilaAppApplication {


	@Autowired
	private ActorRepository actorRepository;
	@Autowired
	private FilmRespository filmRespository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private FilmActorRepository filmActorRepository;
	@Autowired
	private CategoryFilmRepository categoryFilmRepository;

	public AiGeneration ai = new AiGeneration();



	public SakilaAppApplication(ActorRepository actorRepository, FilmRespository filmRespository, CategoryRepository categoryRepository, FilmActorRepository filmActorRepository, CategoryFilmRepository categoryFilmRepository){
		this.actorRepository = actorRepository;
		this.filmRespository = filmRespository;
		this.categoryRepository = categoryRepository;
		this.filmActorRepository = filmActorRepository;
		this.categoryFilmRepository = categoryFilmRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SakilaAppApplication.class, args);
	}
}
