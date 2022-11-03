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
import static org.mockito.Mockito.*;

import javax.servlet.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestSakilaAppApplication {

    RestTemplate restTemplate = new RestTemplate();
    String URL = "http://localhost:8080/filmsapi/1";

    @Autowired
    private MockMvc mockMvc;

    FilmRespository filmRepo = mock(FilmRespository.class);
    ActorRepository actorRepo = mock(ActorRepository.class);
    FilmActorRepository filmActorRepo = mock(FilmActorRepository.class);
    CategoryRepository categoryRepo = mock(CategoryRepository.class);
    CategoryFilmRepository categoryFilmRepo = mock(CategoryFilmRepository.class);
    SakilaAppApplication mockApp = new SakilaAppApplication(actorRepo, filmRepo, categoryRepo, filmActorRepo, categoryFilmRepo);
    SakilaAppApplication mockApp2 = mock(SakilaAppApplication.class);


    @Test
    public void testGetMapping() throws Exception {
        Film test = new Film();
        //200 indicates success
       // mockMvc.perform(MockMvcRequestBuilders.get("/api/films")).andExpect(MockMvcResultMatchers.status().is(200));
        Film expectedFilm = new Film("AFRICAN EGG", "A Fast-Paced Documentary of a Pastry Chef And a Dentist who must Pursue a Forensic Psychologist in The Gulf of Mexico", "2006", 6, 2.99, 130, 22.99, "G");
        mockApp2.getFilmAPI(5);
        verify(mockApp2).getFilmAPI(5);

    }

//    @Test
//    public void testGetMappingFail() throws Exception {
//
//        //200 indicates success
//        //mockMvc.perform(MockMvcRequestBuilders.get("/api/films/12")).andExpect(MockMvcResultMatchers.status().is(200));
//
//    }

}
