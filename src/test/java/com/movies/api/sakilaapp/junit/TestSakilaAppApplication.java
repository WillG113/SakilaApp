package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.*;
import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

class TestSakilaAppApplication {

    SakilaAppApplication app = new SakilaAppApplication();

    @Test
    void testSetAI() {

        AiGeneration newAi = new AiGeneration();

        Assertions.assertNotNull(app.getAi());
        app.setAi(newAi);
        Assertions.assertEquals(newAi, app.getAi());
    }

}
