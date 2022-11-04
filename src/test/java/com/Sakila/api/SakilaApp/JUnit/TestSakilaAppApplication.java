package com.Sakila.api.SakilaApp.JUnit;

import com.Sakila.api.SakilaApp.*;
import org.json.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.*;
import org.springframework.boot.*;
import org.springframework.boot.test.context.*;
import org.springframework.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.web.servlet.*;

import static org.mockito.Mockito.*;

import javax.servlet.*;
import java.util.*;

public class TestSakilaAppApplication {

        @Test
        public void test()
        {
                SakilaAppApplication.main(new String[]{
                        "--spring.main.web-environment=false",
                        "--spring.autoconfigure.exclude=blahblahblah",
                        // Override any other environment properties according to your needs
                });
        }
}
