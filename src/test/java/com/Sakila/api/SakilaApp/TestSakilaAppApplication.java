package com.Sakila.api.SakilaApp;

import io.restassured.internal.common.assertion.*;
import org.json.*;
import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.mock.web.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;
import org.springframework.web.client.*;
import org.springframework.web.context.request.*;
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
    AiGeneration ai = mock(AiGeneration.class);
    SakilaAppApplication mockApp = new SakilaAppApplication(actorRepository, filmRespository, categoryRepository, filmActorRepository, categoryFilmRepository, ai);
    SakilaAppApplication mockApp2 = mock(SakilaAppApplication.class);
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

        when(mockApp.getAllFilmsAPI()).thenReturn(allFilms);

        Iterable<Film> actualResult = mockApp.getAllFilmsAPI();

        Assertions.assertEquals(allFilms, actualResult);

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
        when(ai.postMethod(anyString())).thenReturn("aaaa");
        when(ai.fetchMethod(anyString())).thenReturn("abcd");


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

    @Test
    public void testAddActor() {

        Actor a1 = new Actor("Fname", "Sname");

        when(actorRepository.save(any(Actor.class))).thenReturn(a1);

        Actor a = mockApp.addActor("Fname", "Sname");

        Assertions.assertEquals(a1, a);

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

        when(actorRepository.findAll()).thenReturn(allActors2);
        when(actorRepository.findById(1)).thenReturn(Optional.ofNullable(testActor));
        when(filmRespository.findByActorID(1)).thenReturn(allFilms2);

        when(ai.postMethod(anyString())).thenReturn("aaaa");
        when(ai.fetchMethod(anyString())).thenReturn("abcd");

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
