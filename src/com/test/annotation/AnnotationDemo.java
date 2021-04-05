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
}
