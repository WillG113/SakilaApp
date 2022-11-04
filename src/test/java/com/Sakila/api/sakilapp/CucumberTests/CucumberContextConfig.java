package com.Sakila.api.sakilapp.CucumberTests;

import com.Sakila.api.sakilapp.*;
import io.cucumber.spring.*;
import org.springframework.boot.test.context.*;

@CucumberContextConfiguration
@SpringBootTest(classes = SakilaAppApplication.class)
public class CucumberContextConfig {
}
