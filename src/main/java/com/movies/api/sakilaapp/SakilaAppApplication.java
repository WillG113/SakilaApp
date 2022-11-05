package com.movies.api.sakilaapp;

import com.movies.api.sakilaapp.resources.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.*;


@SpringBootApplication
@RestController
@RequestMapping("/")
public class SakilaAppApplication {

	private AiGeneration ai = new AiGeneration();

	public AiGeneration getAi() {
		return ai;
	}

	public void setAi(AiGeneration ai) {
		this.ai = ai;
	}

	@Generated //Ignore this method from code coverage as it's infeasible to test this method.
	public static void main(String[] args) {
		SpringApplication.run(SakilaAppApplication.class, args);
	}
}
