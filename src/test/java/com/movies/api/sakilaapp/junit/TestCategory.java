package com.movies.api.sakilaapp.junit;

import com.movies.api.sakilaapp.resources.*;
import org.junit.jupiter.api.*;

class TestCategory {

    Category testCategory = new Category(1, "Test");

    Category category = new Category();

    @Test
    void testCategoryConstructor() {

        Assertions.assertEquals(1, testCategory.getCategoryID());
        Assertions.assertEquals("Test", testCategory.getCategoryName());

    }

    @Test
    void setTestCategoryID() {

        Assertions.assertEquals(1, testCategory.getCategoryID());
        testCategory.setCategoryID(5);
        Assertions.assertEquals(5, testCategory.getCategoryID());

    }

    @Test
    void setTestCategoryName() {

        Assertions.assertEquals("Test", testCategory.getCategoryName());
        testCategory.setCategoryName("AnotherTest");
        Assertions.assertEquals("AnotherTest", testCategory.getCategoryName());


    }

}
