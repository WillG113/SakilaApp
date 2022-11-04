package com.Sakila.api.SakilaApp.Resources;

import org.json.*;
import org.springframework.http.*;
import org.springframework.web.client.*;

import java.util.concurrent.*;

public class AiGeneration {

    private RestTemplate restTemplate;

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AiGeneration() {
        this.restTemplate = new RestTemplate();
    }

    public  String fetchMethod(String input) throws JSONException, InterruptedException {

        JSONObject obj;

        do {
            TimeUnit.MILLISECONDS.sleep(250);

            ResponseEntity<String> response = restTemplate.getForEntity("https://stablehorde.net/api/v2/generate/check/" + input, String.class);
            obj = new JSONObject(response.getBody());
        }

        while(!obj.getString("finished").equals("1"));

        ResponseEntity<String> response = restTemplate.getForEntity("https://stablehorde.net/api/v2/generate/status/" + input, String.class);

        obj = new JSONObject(response.getBody());
        JSONArray test = obj.getJSONArray("generations");
        String imageSource = test.getJSONObject(0).getString("img");
        return imageSource;
    }


    public String postMethod(String title) throws JSONException {
        HttpHeaders headers = new HttpHeaders();

        String test = "{\"prompt\": \"" + title + "\"}";
        JSONObject obj = new JSONObject(test);

        headers.add("apikey", "tfgQCv8pHWSRiv8PRP94VA");
        headers.add("accept", "application/json");
        headers.add("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(obj.toString(), headers);

        String input = "https://stablehorde.net/api/v2/generate/async";
        ResponseEntity<String> response = restTemplate.exchange(input, HttpMethod.POST, entity, String.class);

        String responseTest = response.getBody();

        JSONObject objTest = new JSONObject(response.getBody());
        String links = objTest.getString("id");

        return links;

    }

}
