package com.Sakila.api.SakilaApp.JUnit;

import com.Sakila.api.SakilaApp.Controllers.*;
import com.Sakila.api.SakilaApp.Resources.*;
import org.junit.jupiter.api.*;
import org.springframework.web.servlet.*;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TestActorController {


    ActorRepository actorRepository = mock(ActorRepository.class);
    FilmRespository filmRespository = mock(FilmRespository.class);
    CategoryRepository categoryRepository = mock(CategoryRepository.class);
    FilmActorRepository filmActorRepository = mock(FilmActorRepository.class);
    CategoryFilmRepository categoryFilmRepository = mock(CategoryFilmRepository.class);
    //SakilaAppApplication mockApp = new SakilaAppApplication(actorRepository, filmRespository, categoryRepository, filmActorRepository, categoryFilmRepository);
    ActorController mockApp = new ActorController(actorRepository, filmRespository, categoryRepository, filmActorRepository, categoryFilmRepository);
    Film testFilm = new Film("testTitle", "testDesc", "2006", 5, 0.99, 25, 20.99, "PG", 0);
    Film testFilm2 = new Film("testTitle2", "testDesc", "2006", 5, 0.99, 25, 20.99, "PG", 0);
    Iterable<Film> allFilms = Arrays.asList(testFilm, testFilm2);
    List<Film> allFilms2 = Arrays.asList(testFilm, testFilm2);

    Actor testActor = new Actor("TestFName", "TestSName");
    Actor testActor2 = new Actor("TestFName2", "TestSName2");

    Iterable<Actor> allActors = Arrays.asList(testActor, testActor2);
    List<Actor> allActors2 = Arrays.asList(testActor, testActor2);


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
    public void testGetSpecificActor2() throws Exception {

        mockApp.ai = mock(AiGeneration.class);

        when(actorRepository.findById(1)).thenReturn(Optional.empty());
        when(filmRespository.findByActorID(1)).thenReturn(allFilms2);

        when(mockApp.ai.postMethod(anyString())).thenReturn("aaaa");
        when(mockApp.ai.fetchMethod(anyString())).thenReturn("abcd");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("specificActor");
        modelAndView.addObject("actorList", null);
        modelAndView.addObject("filmList", allFilms2);

        ModelAndView actualModel = mockApp.getActor(1);

        Assertions.assertEquals(modelAndView.toString(), actualModel.toString());

    }

    @Test
    public void testGetFilmsByActorID() throws Exception {

        when(actorRepository.findById(5)).thenReturn(Optional.ofNullable(testActor));

        Actor actualResult = mockApp.getActorAPI(5);

        Assertions.assertEquals(testActor, actualResult);

    }

    // POST ---------------
    @Test
    public void testAddActor() {

        Actor a1 = new Actor("Fname", "Sname");

        when(actorRepository.save(any(Actor.class))).thenReturn(a1);

        Actor a = mockApp.addActor("Fname", "Sname");

        Assertions.assertEquals(a1, a);

    }

    // PUT ------
    @Test
    public void testUpdateActor() {

        Actor a1 = new Actor("newName", "newSname");

        when(actorRepository.save(any(Actor.class))).thenReturn(a1);

        Actor a = mockApp.replaceActor(1, "newName", "newSName");

        Assertions.assertEquals(a1, a);

    }

    @Test
    public void testUpdateActor2() {

        Actor a1 = new Actor("newName", "newSname");

        when(actorRepository.save(any(Actor.class))).thenReturn(a1);
        when(actorRepository.findById(1)).thenReturn(Optional.ofNullable(testActor));

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

}
