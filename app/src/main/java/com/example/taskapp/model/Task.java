package com.example.taskapp.model;

public class Task {
    String task;
    String owner;

    public Task(String task, String owner){
        this.task = task;
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public String getTask() {
        return task;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTask(String task) {
        this.task = task;
    }
}

