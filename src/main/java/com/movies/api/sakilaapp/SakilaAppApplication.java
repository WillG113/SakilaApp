package com.movies.api.sakilaapp;

import com.movies.api.sakilaapp.resources.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.*;


@SpringBootApplication
@RestController
@RequestMapping("/")
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

	private AiGeneration ai = new AiGeneration();

	public AiGeneration getAi() {
		return ai;
	}

	public void setAi(AiGeneration ai) {
		this.ai = ai;
	}

	public SakilaAppApplication(ActorRepository actorRepository, FilmRespository filmRespository, CategoryRepository categoryRepository, FilmActorRepository filmActorRepository, CategoryFilmRepository categoryFilmRepository){
		this.actorRepository = actorRepository;
		this.filmRespository = filmRespository;
		this.categoryRepository = categoryRepository;
		this.filmActorRepository = filmActorRepository;
		this.categoryFilmRepository = categoryFilmRepository;
	}

	@Generated
	public static void main(String[] args) {
		SpringApplication.run(SakilaAppApplication.class, args);
	}
}