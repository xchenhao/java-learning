package com.test.reflection;

import org.junit.Test;

public class ReflectionDemo {
    /**
     * 获取 Class 对象的三种形式
     */
    @Test
    public void test() {
        Dog dog = new Dog("wangwang", 3, "black");
 
        // 通过对象的 getClass() 方法
        // Class<? extends Dog> aClass = dog.getClass();
        Class aClass = dog.getClass();

        // 通过类.class
        // Class<Dog> dogClass = Dog.class;
        Class dogClass = Dog.class;

        // 通过 Class.forName 方法
        try {
            // Class<?> aClass1 = Class.forName("com.test.reflection.Dog");
            Class aClass1 = Class.forName("com.test.reflection.Dog");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
