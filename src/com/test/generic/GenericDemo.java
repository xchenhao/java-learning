package com.test.generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenericDemo {

    @Test
    public void test1() {
        List<String> list = new ArrayList<>();
        list.add("vince");
        // list.add(1); 编译期报错
        // list.add(new Object());

        for (int i = 0; i < list.size(); i++) {
            // 如果我们不能确定集合中的元素类型，那么我们需要在处理元素时
            // 需要判断元素的类型，才能做相应的操作
        }
    }

    @Test
    public void testNode() {
        Node<String> stringNode = new Node<>("vince");
        Node<Integer> integerNode = new Node<>(10);

        System.out.println(stringNode.getData());
        System.out.println(integerNode.getData());
    }
}
