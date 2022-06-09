package com.example.reminderapplication;

public class Model {
    String title, time;

    public Model(String title,  String time) {
        this.title = title;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

}
