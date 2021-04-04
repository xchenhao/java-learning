package com.test.json;

public class User {
    private String name;
    private int flolowersCount;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", flolowersCount=" + flolowersCount +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlolowersCount() {
        return flolowersCount;
    }

    public void setFlolowersCount(int flolowersCount) {
        this.flolowersCount = flolowersCount;
    }

}
