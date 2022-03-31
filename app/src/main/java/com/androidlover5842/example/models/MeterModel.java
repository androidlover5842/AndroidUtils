package com.androidlover5842.example.models;

import java.io.Serializable;

public class MeterModel implements Serializable {
    private int position;
    private String text;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
