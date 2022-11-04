package com.Sakila.api.SakilaApp;

import com.Sakila.api.SakilaApp.Resources.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.processing.*;
import javax.persistence.*;


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
