package com.Sakila.api.SakilaApp;

import org.json.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.*;

import static org.mockito.Mockito.*;

import javax.servlet.*;
import java.util.*;

public class TestSakilaAppApplication {

    ActorRepository actorRepository = mock(ActorRepository.class);
    FilmRespository filmRespository = mock(FilmRespository.class);
    CategoryRepository categoryRepository = mock(CategoryRepository.class);
    FilmActorRepository filmActorRepository = mock(FilmActorRepository.class);
    CategoryFilmRepository categoryFilmRepository = mock(CategoryFilmRepository.class);
    SakilaAppApplication mockApp = new SakilaAppApplication(actorRepository, filmRespository, categoryRepository, filmActorRepository, categoryFilmRepository);
    Film testFilm = new Film("testTitle", "testDesc", "2006", 5, 0.99, 25, 20.99, "PG");
    Film testFilm2 = new Film("testTitle2", "testDesc", "2006", 5, 0.99, 25, 20.99, "PG");
    Iterable<Film> allFilms = Arrays.asList(testFilm, testFilm2);
    List<Film> allFilms2 = Arrays.asList(testFilm, testFilm2);

    Actor testActor = new Actor("TestFName", "TestSName");
    Actor testActor2 = new Actor("TestFName2", "TestSName2");

    Iterable<Actor> allActors = Arrays.asList(testActor, testActor2);
    List<Actor> allActors2 = Arrays.asList(testActor, testActor2);


    // Films -----------------------------------------------


    @Test
    public void testGetAllFilmsAPI() {

        when(filmRespository.findAll()).thenReturn(allFilms2);

        Iterable<Film> actualResult = mockApp.getAllFilmsAPI();

        Assertions.assertEquals(allFilms2, actualResult);

    }

    @Test
    public void testGetAllFilms() {

        when(filmRespository.findAll()).thenReturn(allFilms2);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        modelAndView.addObject("filmList", allFilms2);

        ModelAndView actualModel = mockApp.getAllFilms();

        Assertions.assertEquals(modelAndView.toString(), actualModel.toString());

    }

    @Test
    public void testGetSpecificFilmAPI() throws Exception {

        when(filmRespository.findById(5)).thenReturn(Optional.ofNullable(testFilm));

        Film actualResult = mockApp.getFilmAPI(5);

        Assertions.assertEquals(testFilm, actualResult);

    }

    @Test
    public void testGetSpecificFilms() throws JSONException, InterruptedException {

        mockApp.ai = mock(AiGeneration.class);

        CategoryFilm cat1 = new CategoryFilm(1,1,testFilm);
        CategoryFilm cat2 = new CategoryFilm(2,2,testFilm2);
        List<CategoryFilm> catList1 = Arrays.asList(cat1);
        List<CategoryFilm> catList = Arrays.asList(cat1, cat2);

        FilmActor fa1 = new FilmActor(1, 1, testActor);
        FilmActor fa2 = new FilmActor(2,2,testActor2);
        List<FilmActor> faList = Arrays.asList(fa1, fa2);

        when(filmRespository.findById(1)).thenReturn(Optional.ofNullable(testFilm)); //Film Details
        when(categoryFilmRepository.findbyFilmID(1)).thenReturn(catList1); //Find Category by Film
        when(categoryFilmRepository.findCategoryByFilmID(1)).thenReturn("TestCategory"); //Category Name
        when(categoryFilmRepository.findByCategoryID(1)).thenReturn(catList); //SuggestedFilms
        when(filmActorRepository.findByFilmID(1)).thenReturn(faList); //Actors in Film
        when(filmRespository.findById(1)).thenReturn(Optional.ofNullable(testFilm));

        //when(mockApp.postMethod(anyString())).thenReturn("abcd");
        //when(mockApp.fetchMethod(anyString())).thenReturn("a1b2");
        when(mockApp.ai.postMethod(anyString())).thenReturn("aaaa");
        when(mockApp.ai.fetchMethod(anyString())).thenReturn("abcd");


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("specificFilm");
        modelAndView.addObject("film", testFilm);
        modelAndView.addObject("suggestedFilms", catList);
        modelAndView.addObject("category", "TestCategory");
        modelAndView.addObject("actorList", faList);
        modelAndView.addObject("image", "abcd");

        ModelAndView actualModel = mockApp.getFilm(1);

        Assertions.assertEquals(modelAndView.toString(), actualModel.toString());

    }

    // POST ----
    @Test
    public void testAddFilm() {

        Film f1 = new Film("T", "D", "2000", 1, 1.11, 10, 1.11, "PG");

        when(filmRespository.save(any(Film.class))).thenReturn(f1);

        Film f = mockApp.addFilm("T", "D", "2000", 1, 1.11, 10, 1.11, "PG");

        Assertions.assertEquals(f1, f);

    }

    // POST ----
    @Test
    public void testUpdateFilm() {

        Film f1 = new Film("Title", "Desc", "2000", 1, 1.99, 10, 1.99, "PG");

        when(filmRespository.save(any(Film.class))).thenReturn(f1);

        Film f = mockApp.replaceFilm(1,"Title", "Desc", "2000", 1, 1.99, 10, 1.99, "PG");

        Assertions.assertEquals(f1, f);

    }


    // DELETE ----
    @Test
    public void testDeleteFilm() {
        mockApp.deleteFilm(1);
        verify(filmRespository).deleteById(1);
    }

    // ACTORS -------------------------------------------------
    @Test
    public void testGetAllActorsAPI() {

        when(mockApp.getAllActorsAPI()).thenReturn(allActors);

        Iterable<Actor> actualResult = mockApp.getAllActorsAPI();

        Assertions.assertEquals(allActors, actualResult);

    }

    @Test
    public void testGetAllActors() {

        when(actorRepository.findAll()).thenReturn(allActors2);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("actors");
        modelAndView.addObject("actorList", allActors2);

        ModelAndView actualModel = mockApp.getAllActors();

        Assertions.assertEquals(modelAndView.toString(), actualModel.toString());


    }

    @Test
    public void testGetSpecificActorAPI() throws Exception {

        when(actorRepository.findById(5)).thenReturn(Optional.ofNullable(testActor));

        Actor actualResult = mockApp.getActorAPI(5);

        Assertions.assertEquals(testActor, actualResult);

    }

    @Test
    public void testGetSpecificActor() throws Exception {

        mockApp.ai = mock(AiGeneration.class);

        when(actorRepository.findAll()).thenReturn(allActors2);
        when(actorRepository.findById(1)).thenReturn(Optional.ofNullable(testActor));
        when(filmRespository.findByActorID(1)).thenReturn(allFilms2);

        when(mockApp.ai.postMethod(anyString())).thenReturn("aaaa");
        when(mockApp.ai.fetchMethod(anyString())).thenReturn("abcd");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("specificActor");
        modelAndView.addObject("actorList", testActor);
        modelAndView.addObject("filmList", allFilms2);
        modelAndView.addObject("image", "abcd");

        ModelAndView actualModel = mockApp.getActor(1);

        Assertions.assertEquals(modelAndView.toString(), actualModel.toString());

    }

    @Test
    public void testGetFilmsByActorID() throws Exception {

        when(actorRepository.findById(5)).thenReturn(Optional.ofNullable(testActor));

        Actor actualResult = mockApp.getActorAPI(5);

        Assertions.assertEquals(testActor, actualResult);

    }

    @Test
    public void testAddActor() {

        Actor a1 = new Actor("Fname", "Sname");

        when(actorRepository.save(any(Actor.class))).thenReturn(a1);

        Actor a = mockApp.addActor("Fname", "Sname");

        Assertions.assertEquals(a1, a);

    }

    // POST ------
    @Test
    public void testUpdateActor() {

        Actor a1 = new Actor("newName", "newSname");

        when(actorRepository.save(any(Actor.class))).thenReturn(a1);

        Actor a = mockApp.replaceActor(1, "newName", "newSName");

        Assertions.assertEquals(a1, a);

    }

    // DELETE -----
    @Test
    public void testDeleteActor() {

        mockApp.deleteActor(1);
        verify(actorRepository).deleteById(1);

    }

    // FilmActors ------------------------------------------------

    @Test
    public void testGetFilmByActorID() throws Exception {

        when(filmRespository.findByActorID(1)).thenReturn(allFilms2);

        List<Film> actualResult = mockApp.getFilmActorAPI(1);

        Assertions.assertEquals(allFilms2, actualResult);

    }

    // ActorFilms ------------------------------------------------------

    @Test
    public void testGetActorsByFilmID() throws Exception {

        FilmActor fa1 = new FilmActor(1, 1, testActor);
        FilmActor fa2 = new FilmActor(2,2,testActor2);
        List<FilmActor> faList = Arrays.asList(fa1, fa2);

        when(filmActorRepository.findByFilmID(1)).thenReturn(faList);

        List<FilmActor> actualResult = mockApp.getActorFilmsAPI(1);

        Assertions.assertEquals(faList, actualResult);

    }

    // Categories ----------------------------------------

    @Test
    public void testGetCategories() throws Exception {

        Category cat1 = new Category(1, "Test 1");
        Category cat2 = new Category(2, "Test 2");
        Iterable<Category> catList = Arrays.asList(cat1, cat2);
        List<Category> catList2 = Arrays.asList(cat1, cat2);

        when(categoryRepository.findAll()).thenReturn(catList2);

        Iterable<Category> actualResult = mockApp.getAllCategoriesAPI();

        Assertions.assertEquals(catList, actualResult);

    }

    // Films of certain Category --------------------------

    @Test
    public void testGetFilmsByCategory() throws Exception {

        CategoryFilm cat1 = new CategoryFilm(1,1,testFilm);
        CategoryFilm cat2 = new CategoryFilm(2,2,testFilm2);
        List<CategoryFilm> catList = Arrays.asList(cat1, cat2);

        when(categoryFilmRepository.findByCategoryID(1)).thenReturn(catList);

        Iterable<CategoryFilm> actualResult = mockApp.getFilmsByCategoryAPI(1);

        Assertions.assertEquals(catList, actualResult);

    }

}
