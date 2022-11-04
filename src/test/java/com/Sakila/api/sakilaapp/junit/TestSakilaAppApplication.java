package com.Sakila.api.sakilaapp.junit;

import com.Sakila.api.sakilaapp.*;
import com.Sakila.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

class TestSakilaAppApplication {

    SakilaAppApplication app = new SakilaAppApplication(null, null, null, null, null);

    @Test
    void testSetAI() {

        AiGeneration newAi = new AiGeneration();

        Assertions.assertNotNull(app.getAi());
        app.setAi(newAi);
        Assertions.assertEquals(newAi, app.getAi());
    }

}
