package com.test.annotation;

import java.util.Arrays;

@MyAnnotation(name = "bin", like = {"金鱼","鱼干"}, color = Color.GREEN)
public class Cat {
    private String name;
    private int age;

    private String[] like;

    public String[] getLike() {
        return like;
    }

    public void setLike(String[] like) {
        this.like = like;
    }

    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

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

    public Cat() {
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 重写了父类的 toString 方法
    // 用于验证方法是否覆盖父类中的方法
    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", like=" + Arrays.toString(like) +
                ", color=" + color +
                '}';
    }

    // 用于标记方法已过时，不建议使用
    @Deprecated
    public String printInfo() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
