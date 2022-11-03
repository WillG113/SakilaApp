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

    @Test
    public void testGetMapping() {

        Mockito.when(mockApp.getAllFilmsAPI()).thenReturn(allFilms);

        Iterable<Film> actualResult = mockApp.getAllFilmsAPI();

        Assertions.assertEquals(allFilms, actualResult);

    }



}
