package com.Sakila.api.SakilaApp.Resources;

import org.json.*;
import org.springframework.http.*;
import org.springframework.web.client.*;

import java.util.concurrent.*;

public class AiGeneration {

    public RestTemplate restTemplate;

    public AiGeneration() {
        this.restTemplate = new RestTemplate();
    }

    public  String fetchMethod(String URL) throws JSONException, InterruptedException {

        JSONObject obj;

        do {
            TimeUnit.MILLISECONDS.sleep(250);

            ResponseEntity<String> response = restTemplate.getForEntity("https://stablehorde.net/api/v2/generate/check/" + URL, String.class);
            obj = new JSONObject(response.getBody());
            System.out.println("trying - queue position: " + obj.getString("queue_position"));
        }

        while(!obj.getString("finished").equals("1"));

        ResponseEntity<String> response = restTemplate.getForEntity("https://stablehorde.net/api/v2/generate/status/" + URL, String.class);

        obj = new JSONObject(response.getBody());
        JSONArray test = obj.getJSONArray("generations");
        String imageSource = test.getJSONObject(0).getString("img");
        System.out.println("done");
        return imageSource;
    }


    public String postMethod(String title) throws JSONException {
        HttpHeaders headers = new HttpHeaders();

        String test = "{\"prompt\": \"" + title + "\"}";
        //String test = "{\n" + "  \"prompt\": \"" + title + "\",\n" + "  \"params\": {\n" + "    \"height\": 1024,\n" + "    \"width\": 1024\n" + "  }\n" +	"}";
        JSONObject obj = new JSONObject(test);

        //headers.add("apikey", "ob3dJ5IV9yr2bDppOIpeRw");
        headers.add("apikey", "tfgQCv8pHWSRiv8PRP94VA");
        headers.add("accept", "application/json");
        headers.add("Content-Type", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(obj.toString(), headers);

        String URL = "https://stablehorde.net/api/v2/generate/async";
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

        String responseTest = response.getBody();
        System.out.println(responseTest);

        JSONObject objTest = new JSONObject(response.getBody());
        String links = objTest.getString("id");

        return links;

    }

}
