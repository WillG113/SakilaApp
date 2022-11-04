package com.Sakila.api.SakilaApp.JUnit;

import com.Sakila.api.SakilaApp.*;
import org.json.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;
import org.springframework.boot.*;
import org.springframework.boot.builder.*;
import org.springframework.boot.test.context.*;
import org.springframework.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.web.servlet.*;

import static org.mockito.Mockito.*;

import javax.servlet.*;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TestSakilaAppApplication {

        @Test
       public void test() {
                String array[] = {};
                SakilaAppApplication app = mock(SakilaAppApplication.class);
                SakilaAppApplication.main(array);
                verify(SakilaAppApplication.main(array));
       }

}
