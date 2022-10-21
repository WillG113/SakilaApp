package com.Sakila.api.SakilaApp;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;
import org.springframework.web.servlet.*;

import java.util.*;


@SpringBootApplication
@RestController
@RequestMapping("/")
@CrossOrigin
public class SakilaAppApplication {

	@Autowired
	private ActorRepository actorRepository;
	private FilmRespository filmRespository;
	private AddressRepository addressRepository;
	private FilmActorRepository filmActorRepository;

	public SakilaAppApplication(ActorRepository actorRepository, FilmRespository filmRespository, AddressRepository addressRepository, FilmActorRepository filmActorRepository){
		this.actorRepository = actorRepository;
		this.filmRespository = filmRespository;
		this.addressRepository = addressRepository;
		this.filmActorRepository = filmActorRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(SakilaAppApplication.class, args);
	}

	@GetMapping("/actors")
	public ModelAndView getAllActors() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("actors");
		modelAndView.addObject("actorList", actorRepository.findAll());
		return modelAndView;
	}

	@GetMapping("/actors/{id}")
	public ModelAndView getActor(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("actors");
		modelAndView.addObject("actorList", actorRepository.findById(id).orElseThrow(() -> new ResourceAccessException("Actor not found")));
		return modelAndView;
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
	public ModelAndView getAllFilms() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("films");
		modelAndView.addObject("filmList", filmRespository.findAll());
		return modelAndView;
	}

	@GetMapping("/films/{id}")
	public ModelAndView getFilm(@PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("specificFilm");
		modelAndView.addObject("film", filmRespository.findById(id).orElseThrow(() -> new IndexOutOfBoundsException()));
		modelAndView.addObject("actorList", filmActorRepository.findByFilmID(id));
		System.out.println(filmActorRepository.findByFilmID(id));
		return modelAndView;
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

	// ------------------------------------------------------

	@GetMapping("/address")
	Iterable<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

	@GetMapping("/address/{id}")
	Address getAddress(@PathVariable int id) {
		return addressRepository.findById(id).orElseThrow(() -> new IndexOutOfBoundsException());
	}

	@PostMapping("/address/{address}+{district}+{postcode}+{cityID}")
	public Address addAddress(@PathVariable String address, @PathVariable String district, @PathVariable String postcode,@PathVariable String cityID){
		Address newAddress = new Address(address, district, postcode, cityID);
		return addressRepository.save(newAddress);
	}

	@PutMapping("/address/{id}+{address}+{district}+{postcode}+{cityID}")
	public Address replaceAddress(@PathVariable int id, @PathVariable String address, @PathVariable String district, @PathVariable String postcode, @PathVariable String cityid){
		Address newAddress = new Address(address, district, postcode, cityid);

		return addressRepository.findById(id).map(actor -> {
			newAddress.setAddress(newAddress.getAddress());
			newAddress.setDistrict((newAddress.getDistrict()));
			newAddress.setPostcode(newAddress.getPostcode());
			newAddress.setCityID(newAddress.getCityID());
			return addressRepository.save(actor);
		}).orElseGet(() -> {
			newAddress.setAddressID(id);
			return addressRepository.save(newAddress);
		});
	}

	@DeleteMapping("/address/{id}")
	public void deleteAddress(@PathVariable int id) {
		addressRepository.deleteById(id);
	}

}
