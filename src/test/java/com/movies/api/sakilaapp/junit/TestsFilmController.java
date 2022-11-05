package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.controllers.*;
import com.movies.api.sakilaapp.resources.*;
import org.json.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.*;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class TestsFilmController {


    ActorRepository actorRepository = mock(ActorRepository.class);
    FilmRespository filmRespository = mock(FilmRespository.class);
    CategoryRepository categoryRepository = mock(CategoryRepository.class);
    FilmActorRepository filmActorRepository = mock(FilmActorRepository.class);
    CategoryFilmRepository categoryFilmRepository = mock(CategoryFilmRepository.class);
    StockRepository stockRepository = mock(StockRepository.class);
    //SakilaAppApplication mockApp = new SakilaAppApplication(actorRepository, filmRespository, categoryRepository, filmActorRepository, categoryFilmRepository);
    FilmController mockApp = new FilmController(actorRepository, filmRespository, categoryRepository, filmActorRepository, categoryFilmRepository, stockRepository);
    Film testFilm = new Film("testTitle", "testDesc", "2006", 5, 0.99, 25, 20.99, "PG", 0);
    Film testFilm2 = new Film("testTitle2", "testDesc", "2006", 5, 0.99, 25, 20.99, "PG", 0);
    Iterable<Film> allFilms = Arrays.asList(testFilm, testFilm2);
    List<Film> allFilms2 = Arrays.asList(testFilm, testFilm2);

    Actor testActor = new Actor("TestFName", "TestSName");
    Actor testActor2 = new Actor("TestFName2", "TestSName2");

    Iterable<Actor> allActors = Arrays.asList(testActor, testActor2);
    List<Actor> allActors2 = Arrays.asList(testActor, testActor2);

    @Test
    void testGetAllFilmsAPI() {

        when(filmRespository.findAll()).thenReturn(allFilms2);

        Iterable<Film> actualResult = mockApp.getAllFilmsAPI();

        Assertions.assertEquals(allFilms2, actualResult);

    }

    @Test
    void testGetAllFilms() {

        when(filmRespository.findAll()).thenReturn(allFilms2);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        modelAndView.addObject("filmList", allFilms2);

        ModelAndView actualModel = mockApp.getAllFilms();

        Assertions.assertEquals(modelAndView.toString(), actualModel.toString());

    }

    @Test
    void testGetSpecificFilmAPI() throws Exception {

        when(filmRespository.findById(5)).thenReturn(Optional.ofNullable(testFilm));

        Film actualResult = mockApp.getFilmAPI(5);

        Assertions.assertEquals(testFilm, actualResult);

    }

    @Test
    void testGetSpecificFilms() throws JSONException, InterruptedException {

        mockApp.setAi(mock(AiGeneration.class));

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
        when(mockApp.getAi().postMethod(anyString())).thenReturn("aaaa");
        when(mockApp.getAi().fetchMethod(anyString())).thenReturn("abcd");


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
    void testGetSpecificFilms2() throws JSONException, InterruptedException {

        mockApp.setAi(mock(AiGeneration.class));

        CategoryFilm cat1 = new CategoryFilm(1,1,testFilm);
        CategoryFilm cat2 = new CategoryFilm(2,2,testFilm2);
        List<CategoryFilm> catList1 = Arrays.asList(cat1);
        List<CategoryFilm> catList = Arrays.asList(cat1, cat2);

        FilmActor fa1 = new FilmActor(1, 1, testActor);
        FilmActor fa2 = new FilmActor(2,2,testActor2);
        List<FilmActor> faList = Arrays.asList(fa1, fa2);

        when(filmRespository.findById(1)).thenReturn(Optional.ofNullable(testFilm)).thenReturn(Optional.empty()); //Film Details
        when(categoryFilmRepository.findbyFilmID(1)).thenReturn(catList1); //Find Category by Film
        when(categoryFilmRepository.findCategoryByFilmID(1)).thenReturn("TestCategory"); //Category Name
        when(categoryFilmRepository.findByCategoryID(1)).thenReturn(catList); //SuggestedFilms
        when(filmActorRepository.findByFilmID(1)).thenReturn(faList); //Actors in Film

        //when(mockApp.postMethod(anyString())).thenReturn("abcd");
        //when(mockApp.fetchMethod(anyString())).thenReturn("a1b2");
        when(mockApp.getAi().postMethod(anyString())).thenReturn("aaaa");
        when(mockApp.getAi().fetchMethod(anyString())).thenReturn("abcd");


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("specificFilm");
        modelAndView.addObject("film", testFilm);
        modelAndView.addObject("suggestedFilms", catList);
        modelAndView.addObject("category", "TestCategory");
        modelAndView.addObject("actorList", faList);

        ModelAndView actualModel = mockApp.getFilm(1);

        Assertions.assertEquals(modelAndView.toString(), actualModel.toString());

    }

    // POST ----
    @Test
    void testAddFilm() {

        Film f1 = new Film("T", "D", "2000", 1, 1.11, 10, 1.11, "PG", 0);

        when(filmRespository.save(any(Film.class))).thenReturn(f1);

        Film f = mockApp.addFilm("T", "D", "2000", 1, 1.11, 10, 1.11, "PG");

        Assertions.assertEquals(f1, f);

    }

    // PUT ----
    @Test
    void testUpdateFilm() {

        Film f1 = new Film("Title", "Desc", "2000", 1, 1.99, 10, 1.99, "PG", 0);

        when(filmRespository.save(any(Film.class))).thenReturn(f1);

        Film f = mockApp.replaceFilm(1,"Title", "Desc", "2000", 1, 1.99, 10, 1.99, "PG");

        Assertions.assertEquals(f1, f);

    }

    @Test
    void testUpdateFilm2() {

        Film f1 = new Film("Title", "Desc", "2000", 1, 1.99, 10, 1.99, "PG", 0);

        when(filmRespository.save(any(Film.class))).thenReturn(f1);
        when(filmRespository.findById(1)).thenReturn(Optional.ofNullable(testFilm));

        Film f = mockApp.replaceFilm(1,"Title", "Desc", "2000", 1, 1.99, 10, 1.99, "PG");

        Assertions.assertEquals(f1, f);

    }


    // DELETE ----
    @Test
    void testDeleteFilm() {
        mockApp.deleteFilm(1);
        verify(filmRespository).deleteById(1);
    }



}
