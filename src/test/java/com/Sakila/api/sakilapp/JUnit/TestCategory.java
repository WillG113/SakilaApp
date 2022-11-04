package com.Sakila.api.sakilapp.JUnit;

import com.Sakila.api.sakilapp.Resources.*;
import org.junit.jupiter.api.*;

public class TestCategory {

    Category testCategory = new Category(1, "Test");

    Category category = new Category();

    @Test
    public void testCategoryConstructor() {

        Assertions.assertEquals(1, testCategory.getCategoryID());
        Assertions.assertEquals("Test", testCategory.getCategoryName());

    }

    @Test
    public void setTestCategoryID() {

        Assertions.assertEquals(1, testCategory.getCategoryID());
        testCategory.setCategoryID(5);
        Assertions.assertEquals(5, testCategory.getCategoryID());

    }

    @Test
    public void setTestCategoryName() {

        Assertions.assertEquals("Test", testCategory.getCategoryName());
        testCategory.setCategoryName("AnotherTest");
        Assertions.assertEquals("AnotherTest", testCategory.getCategoryName());


    }

}
