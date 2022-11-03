package com.Sakila.api.SakilaApp.CucumberTests;

import com.Sakila.api.SakilaApp.*;
import io.cucumber.spring.*;
import org.springframework.boot.test.context.*;

@CucumberContextConfiguration
@SpringBootTest(classes = SakilaAppApplication.class)
public class CucumberContextConfig {
}
