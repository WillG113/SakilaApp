package com.Sakila.api.SakilaApp;

import org.json.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;
import org.springframework.web.servlet.*;


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
	public ModelAndView getActor(@PathVariable int id) throws JSONException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("specificActor");
		modelAndView.addObject("actorList", actorRepository.findById(id).orElseThrow(() -> new ResourceAccessException("Actor not found")));
		modelAndView.addObject("filmList", filmRespository.findByActorID(id));

//		// REST REQUEST FUNCTION
//		Actor input = actorRepository.findById(id).orElse(input = null);
//		String name = input.getActorFirstname() + " " + input.getActorLastname();
//		if(input != null) {
//			modelAndView.addObject("image", fetchMethod(postMethod(name)));
//		}

		return modelAndView;
	}

	@GetMapping("/api/actors/{id}")
	Actor getActorAPI(@PathVariable int id) {
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
	public ModelAndView getFilm(@PathVariable int id) throws JSONException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("specificFilm");
		modelAndView.addObject("film", filmRespository.findById(id).orElseThrow(() -> new IndexOutOfBoundsException()));
		modelAndView.addObject("actorList", filmActorRepository.findByFilmID(id));
		System.out.println(filmActorRepository.findByFilmID(id));

//		// REST REQUEST FUNCTION
//		Film input = filmRespository.findById(id).orElse(input = null);
//
//		if(input != null) {
//			modelAndView.addObject("image", fetchMethod(postMethod(input.getDesc())));
//		}

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

	@GetMapping("/api/address")
	Iterable<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

	@GetMapping("/api/address/{id}")
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

	public class ResponseImage {
		private String output;

		public String getOutput() {
			return output;
		}
	}


	// --------------------------------- OTHER METHODS ----

	public String fetchMethod(String URL) throws JSONException {
		RestTemplate restTemplate= new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.add("Authorization", "Token eb17167cb82a167f776e27b2c6ef2039b1a4d8c8");
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		JSONObject obj;

		do {

			ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
			String test = response.getBody();

			obj = new JSONObject(response.getBody());
			System.out.println("trying");
		}

		while(!obj.getString("status").equals("succeeded"));

		String imageSource = (String) obj.getJSONArray("output").get(0);
		System.out.println("done");
		return imageSource;
	}


	public String postMethod(String title) throws JSONException {

		RestTemplate restTemplate= new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		String test = "{\n" +
				"  \"version\": \"5b703f0fa41880f918ab1b12c88a25b468c18639be17515259fb66a83f4ad0a4\",\n" +
				"  \"input\": {\n" +
				"    \"prompt\": \"" + title + "\"\n" +
				"  }\n" +
				"}";
		JSONObject obj = new JSONObject(test);

		headers.add("Authorization", "Token eb17167cb82a167f776e27b2c6ef2039b1a4d8c8");
		HttpEntity<String> entity = new HttpEntity<>(obj.toString(), headers);

		String URL = "https://api.replicate.com/v1/predictions";
		ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

		String responseTest = response.getBody();

		JSONObject objTest = new JSONObject(response.getBody());
		String links = objTest.getJSONObject("urls").getString("get");

		return links;

	}

}
