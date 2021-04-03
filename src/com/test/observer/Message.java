package com.test.observer;

import java.util.ArrayList;
import java.util.List;

// 具体的被观察者
public class Message implements MessageSubject{
    // 维护的观察者列表
    private List<Observer> list = new ArrayList<>();
    private String message;

    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        list.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < list.size(); i++){
            Observer observer = list.get(i);
            observer.update(message);
        }
    }

}
