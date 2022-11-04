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
        public void testMain() {
                SakilaAppApplication.main(new String[] {});
                Assertions.assertTrue(true);
        }
}
