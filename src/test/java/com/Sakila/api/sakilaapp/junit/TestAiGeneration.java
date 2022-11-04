package com.Sakila.api.sakilaapp.junit;

import com.Sakila.api.sakilaapp.resources.*;
import org.json.*;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.*;
import static org.mockito.Mockito.*;

public class TestAiGeneration {

    @Test
    void testConstructor() {
        AiGeneration ai = new AiGeneration();
        Assertions.assertNotNull(ai.getRestTemplate());
    }

    @Test
    void testFetchMethod() throws JSONException, InterruptedException {

        AiGeneration ai = new AiGeneration();

        String dummyResponse = "{\"finished\": 1, \"processing\": 0, \"restarted\": 0, \"waiting\": 0, \"done\": true, \"faulted\": false, \"wait_time\": 0, \"queue_position\": 0, \"kudos\": 10.83, \"is_possible\": true}";

        String otherDummyResponse = "{\"generations\": [{\"img\": \"1234\", \"seed\": \"3603702987\", \"worker_id\": \"1405eb95-9d06-4fe6-8d67-db554a378951\", \"worker_name\": \"Angulimala\", \"model\": \"stable_diffusion\"}], \"finished\": 1, \"processing\": 0, \"restarted\": 0, \"waiting\": 0, \"done\": true, \"faulted\": false, \"wait_time\": 0, \"queue_position\": 0, \"kudos\": 10.83, \"is_possible\": true}\n";

        ResponseEntity<String> dummy = new ResponseEntity<String>(dummyResponse, HttpStatus.ACCEPTED);
        ResponseEntity<String> dummy2 = new ResponseEntity<String>(otherDummyResponse, HttpStatus.ACCEPTED);

        ai.setRestTemplate(mock(RestTemplate.class));

        when(ai.getRestTemplate().getForEntity("https://stablehorde.net/api/v2/generate/check/a", String.class)).thenReturn(dummy);
        when(ai.getRestTemplate().getForEntity("https://stablehorde.net/api/v2/generate/status/a", String.class)).thenReturn(dummy2);

        String actual = ai.fetchMethod("a");

        Assertions.assertEquals("1234", actual);
    }

    @Test
    void testFetchMethod2() throws JSONException, InterruptedException {

        AiGeneration ai = new AiGeneration();

        String dummyResponse = "{\"finished\": 0, \"processing\": 0, \"restarted\": 0, \"waiting\": 0, \"done\": true, \"faulted\": false, \"wait_time\": 0, \"queue_position\": 0, \"kudos\": 10.83, \"is_possible\": true}";

        String dummyResponse2 = "{\"finished\": 1, \"processing\": 0, \"restarted\": 0, \"waiting\": 0, \"done\": true, \"faulted\": false, \"wait_time\": 0, \"queue_position\": 0, \"kudos\": 10.83, \"is_possible\": true}";

        String otherDummyResponse = "{\"generations\": [{\"img\": \"1234\", \"seed\": \"3603702987\", \"worker_id\": \"1405eb95-9d06-4fe6-8d67-db554a378951\", \"worker_name\": \"Angulimala\", \"model\": \"stable_diffusion\"}], \"finished\": 1, \"processing\": 0, \"restarted\": 0, \"waiting\": 0, \"done\": true, \"faulted\": false, \"wait_time\": 0, \"queue_position\": 0, \"kudos\": 10.83, \"is_possible\": true}\n";

        ResponseEntity<String> dummy = new ResponseEntity<String>(dummyResponse, HttpStatus.ACCEPTED);
        ResponseEntity<String> dummy2 = new ResponseEntity<String>(otherDummyResponse, HttpStatus.ACCEPTED);
        ResponseEntity<String> dummy3= new ResponseEntity<String>(dummyResponse2, HttpStatus.ACCEPTED);

        ai.setRestTemplate(mock(RestTemplate.class));

        when(ai.getRestTemplate().getForEntity("https://stablehorde.net/api/v2/generate/check/a", String.class)).thenReturn(dummy).thenReturn(dummy3);
        when(ai.getRestTemplate().getForEntity("https://stablehorde.net/api/v2/generate/status/a", String.class)).thenReturn(dummy2);

        String actual = ai.fetchMethod("a");

        Assertions.assertEquals("1234", actual);
    }

    @Test
    void testPostMethod() throws JSONException {


        AiGeneration ai = new AiGeneration();

        String dummyResponse = "{\"id\": \"abcdefg\"}\n";

        ai.setRestTemplate(mock(RestTemplate.class));

        String test = "{\"prompt\": \"" + "a" + "\"}";
        JSONObject obj = new JSONObject(test);

        HttpHeaders headers = new HttpHeaders();
        headers.add("apikey", "tfgQCv8pHWSRiv8PRP94VA");
        headers.add("accept", "application/json");
        headers.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(obj.toString(), headers);

        String URL = "https://stablehorde.net/api/v2/generate/async";
        ResponseEntity<String> dummy = new ResponseEntity<>(dummyResponse, HttpStatus.ACCEPTED);

        when(ai.getRestTemplate().exchange(URL, HttpMethod.POST, entity, String.class)).thenReturn(dummy);

        String result = ai.postMethod("a");

        Assertions.assertEquals("abcdefg", result);


    }
}
