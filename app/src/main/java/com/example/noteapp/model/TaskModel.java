package com.example.noteapp.model;

import java.io.Serializable;

public class TaskModel implements Serializable {
    private String title;

    public TaskModel(String txtTitle) {
        this.title = txtTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTxtTitle(String txtTitle) {
        this.title = txtTitle;
    }
}
