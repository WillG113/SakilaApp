package com.Sakila.api.SakilaApp;

import org.json.*;
import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.*;

public class TestSakilaAppApplication {

    RestTemplate restTemplate = new RestTemplate();
    String URL = "http://localhost:8080/filmsapi/1";



    @Test
    public void testGetMapping() {
/*
        String expectedObj = "{\"filmID\":1,\"title\":\"TEST FILM\",\"desc\":\"Warren Nolte\",\"length\":25}";

        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
        System.out.println(response);
        Assertions.assertEquals(expectedObj, response.getBody());


 */
    }

}
