package com.Sakila.api.SakilaApp;

import io.restassured.internal.common.assertion.*;
import org.json.*;
import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.mock.web.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;
import org.springframework.web.client.*;
import org.springframework.web.context.request.*;

import static org.mockito.Mockito.*;

import javax.servlet.*;
import java.util.*;

public class TestSakilaAppApplication {



    FilmRespository filmRepo = mock(FilmRespository.class);
    ActorRepository actorRepo = mock(ActorRepository.class);
    FilmActorRepository filmActorRepo = mock(FilmActorRepository.class);
    CategoryRepository categoryRepo = mock(CategoryRepository.class);
    CategoryFilmRepository categoryFilmRepo = mock(CategoryFilmRepository.class);
    SakilaAppApplication mockApp = new SakilaAppApplication(actorRepo, filmRepo, categoryRepo, filmActorRepo, categoryFilmRepo);
    SakilaAppApplication mockApp2 = mock(SakilaAppApplication.class);


    @Test
    public void testGetMapping() throws Exception {
        Film f = new Film("testTitle", "testDesc", "2006", 5, 0.99, 25, 20.99, "PG");

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(mockApp2.getFilmAPI(5)).thenReturn(f);
        Film responseBody = mockApp2.getFilmAPI(5);

        Assertions.assertEquals(f, responseBody);
    }



}
