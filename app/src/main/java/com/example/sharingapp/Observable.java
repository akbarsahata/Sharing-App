package com.example.sharingapp;

import java.util.ArrayList;

public class Observable {
    private ArrayList<Observer> observers = null;

    public Observable(){
        observers = new ArrayList<Observer>();
    }

    public void addObserver(Observer o){
        observers.add(o);
    }

    public void removeObserver(Observer o){
        observers.remove(o);
    }

    public void notifyObservers(){
        for (Observer o : observers){
            o.update();
        }
    }
}
