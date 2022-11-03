package com.Sakila.api.SakilaApp;

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

import javax.servlet.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TestSakilaAppApplication {

    RestTemplate restTemplate = new RestTemplate();
    String URL = "http://localhost:8080/filmsapi/1";

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetMapping() throws Exception {

        //200 indicates success
       // mockMvc.perform(MockMvcRequestBuilders.get("/api/films")).andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    public void testGetMappingFail() throws Exception {

        //200 indicates success
        //mockMvc.perform(MockMvcRequestBuilders.get("/api/films/12")).andExpect(MockMvcResultMatchers.status().is(200));

    }

}
