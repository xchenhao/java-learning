package com.test.reflection;

public class Dog {
    private String name;
    private int age;
    private String color;

    public int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private void set() {
        System.out.println("调用了 set 方法");
    }
    protected void get() {
        System.out.println("调用了 get 方法");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }

    public Dog(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    // 最好都要保留默认的无参构造方法，通过反射 newInstance() 可生成对象
    public Dog() {
    }
}
