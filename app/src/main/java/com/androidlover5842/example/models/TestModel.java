package com.androidlover5842.example.models;

import java.io.Serializable;

public class TestModel implements Serializable {
    private String firstName;
    private String lastName;
    private int position;

    public TestModel() {
    }

    public TestModel(String firstName, String lastName,int position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position=position;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
