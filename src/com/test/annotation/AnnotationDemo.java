package com.test.annotation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

// 消除警告信息
@SuppressWarnings("all")
public class AnnotationDemo {

    @Test
    public void test() {
        Cat cat = new Cat("miaomiao", 3);
        cat.printInfo();  // 调用了一个已过时的方法

        List list = new ArrayList<>();
        list.add("vince");
        list.add(10);
        list.add(10.1f);
    }

    // 通过反射处理注解
    @Test
    public void test2() {
        Class<Cat> catClass = Cat.class;
        // 获取类上应用的指定注解
        MyAnnotation annotation = catClass.getAnnotation(MyAnnotation.class);
        // 获取注解上的变量值
        String name = annotation.name();
        int age = annotation.age();
        Color color = annotation.color();
        String[] like = annotation.like();

        try {
            Cat cat = catClass.newInstance();
            cat.setName(name);
            cat.setAge(age);
            cat.setColor(color);
            cat.setLike(like);

            System.out.println(cat);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
