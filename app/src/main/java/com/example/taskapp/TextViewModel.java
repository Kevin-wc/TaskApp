package com.example.taskapp;

import androidx.lifecycle.ViewModel;

import java.util.LinkedList;

public class TextViewModel extends ViewModel {
    private LinkedList<String> tasks;
    TextViewModel textModel;
    LinkedList<String> l = new LinkedList<String>();

    public TextViewModel(){
        tasks = new LinkedList<>();
        tasks.add(l.toString());
    }

    public void setTasks(LinkedList<String> l){
        tasks.add(l.toString());
    }
    public LinkedList<String> getTasks(){
        return tasks;
    }

    public void addTask(String s){
        LinkedList<String> l = getTasks();
        l.add(s);

    }




}
