package com.ace.ucv.model;

/**
 * Created by Andreea Draghici on 10/12/2023
 * Name of project: HttpCalls
 */
public class ToDo {

    private String title;
    private boolean completed;

    public ToDo(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
}
