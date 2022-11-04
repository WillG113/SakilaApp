package com.movies.api.sakilaapp.cucumber;

import com.movies.api.sakilaapp.*;
import io.cucumber.spring.*;
import org.springframework.boot.test.context.*;

@CucumberContextConfiguration
@SpringBootTest(classes = SakilaAppApplication.class)
public class CucumberContextConfig {
}
