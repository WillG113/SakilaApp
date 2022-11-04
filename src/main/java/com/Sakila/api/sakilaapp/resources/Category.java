package com.Sakila.api.sakilaapp.resources;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "category_id")
    int categoryID;

    @Column(name = "name")
    String categoryName;

    public Category(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public Category() {

    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
