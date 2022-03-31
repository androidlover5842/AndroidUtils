package com.androidlover5842.example.models;

import java.io.Serializable;

public class TestModel implements Serializable {
    private String firstName;
    private String lastName;

    public TestModel() {
    }

    public TestModel(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
