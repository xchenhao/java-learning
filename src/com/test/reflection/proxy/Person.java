package com.test.reflection.proxy;

public class Person implements Subject, Hotel {
    @Override
    public void shopping() {
        System.out.println("付款，买到心仪的比基尼");
    }

    @Override
    public void reserve() {
        System.out.println("付预定款，打飞的前往目的地");
    }
}
