package com.test.observer;

// 被观察者的接口
public interface MessageSubject {
    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObservers();
}
