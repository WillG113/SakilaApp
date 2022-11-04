package com.Sakila.api.sakilaapp.cucumber;

import com.Sakila.api.sakilaapp.*;
import io.cucumber.spring.*;
import org.springframework.boot.test.context.*;

@CucumberContextConfiguration
@SpringBootTest(classes = SakilaAppApplication.class)
public class CucumberContextConfig {
}
