package com.Sakila.api.SakilaApp.JUnit;

import com.Sakila.api.SakilaApp.Resources.*;
import org.json.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.web.client.*;
import static org.mockito.Mockito.*;

import java.util.*;
import java.util.concurrent.*;

public class TestAiGeneration {

    @Test
    public void testFetchMethod() throws JSONException, InterruptedException {

        AiGeneration ai = new AiGeneration();

        String dummyResponse = "{\"finished\": 1, \"processing\": 0, \"restarted\": 0, \"waiting\": 0, \"done\": true, \"faulted\": false, \"wait_time\": 0, \"queue_position\": 0, \"kudos\": 10.83, \"is_possible\": true}";

        String otherDummyResponse = "{\"generations\": [{\"img\": \"1234\", \"seed\": \"3603702987\", \"worker_id\": \"1405eb95-9d06-4fe6-8d67-db554a378951\", \"worker_name\": \"Angulimala\", \"model\": \"stable_diffusion\"}], \"finished\": 1, \"processing\": 0, \"restarted\": 0, \"waiting\": 0, \"done\": true, \"faulted\": false, \"wait_time\": 0, \"queue_position\": 0, \"kudos\": 10.83, \"is_possible\": true}\n";

        ResponseEntity<String> dummy = new ResponseEntity<String>(dummyResponse, HttpStatus.ACCEPTED);
        ResponseEntity<String> dummy2 = new ResponseEntity<String>(otherDummyResponse, HttpStatus.ACCEPTED);

        ai.setRestTemplate(mock(RestTemplate.class));

        when(ai.restTemplate.getForEntity("https://stablehorde.net/api/v2/generate/check/a", String.class)).thenReturn(dummy);
        when(ai.restTemplate.getForEntity("https://stablehorde.net/api/v2/generate/status/a", String.class)).thenReturn(dummy2);

        String actual = ai.fetchMethod("a");

        Assertions.assertEquals("1234", actual);
    }
}
