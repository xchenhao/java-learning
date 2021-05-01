package com.test.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Collection 接口：用于存储单个对象的集合
 * List 接口：
 * （1）有序的
 * （2）允许多个 null 元素
 * （3）具体的常用实现类：ArrayList, Vector, LinkedList
 * Set 接口：
 */
public class ListDemo {

    /**
     * ArrayList
     */
    @Test
    public void arrayList()
    {
        // 使用集合来存储多个不同类型的元素（对象），那么在处理时会比较麻烦，在实际开发中不建议这样使用
        // 我们应该在一个集合中存储相同类型的对象
        List list = new ArrayList<>();
        list.add("苍老师");
        list.add("李老师");
        list.add("张老师");
        list.add("毕老师");
        //list.add(10);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // 泛型
        List<String> list2 = new ArrayList<>();
        // List<String> list2 = new ArrayList<String>(); // JDK 1.7 之前需要加个 <String>
        list2.add("ABC");
        list2.add("CDE");
        // list2.add(1); 编译期即可检查
        int size = list2.size();
        for (int i = 0; i < size; i++) {  // 遍历集合
            System.out.println(list2.get(i));
        }


        System.out.println(list.contains("苍老师"));
        list.remove("毕老师");
        System.out.println(list.size());

        String[] array = (String[]) list.toArray(new String[]{});
        for (String s: array) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {

    }
}
