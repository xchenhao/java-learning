package com.test.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
     * Vector JDK 1.0 引入
     * 1. 实现原理：采用动态对象数组实现，默认构造方法创建一个大小为 10 的对象数组
     * 2. 扩充的算法：当增量为 0 时，扩充为原来大小的 2 倍；当增量大于 0 时，扩充为原来大小 + 增量
     * 3. 不适合删除或插入操作
     * 4. 为了防止数组动态扩充次数过多，建议创建 Vector 时，给定初始容量
     * 5. 线程安全，适合在多线程时访问时使用，在单线程下使用效率较低
     *
     * 面试题：Vector 与 ArrayList 的区别？
     */
    @Test
    public void vector() {
        Vector<String> v = new Vector<>();
        v.add("A");
        v.add("B");
        v.add("C");

        for (int i = 0; i < v.size(); i++) {
            System.out.println(v.get(i));
        }
    }

    /**
     * ArrayList JDK 1.2 引入（1.8 API）
     * 1. 实现原理：采用动态对象数组实现，默认构造方法创建了一个空数组
     * 2. 第一次添加元素，扩展容量为 10，之后的扩充算法：原来数组大小 + 原来数组的一半
     * 3. 不适合进行删除或插入操作
     * 4. 为了防止数组动态扩充次数过多，建议创建 ArrayList 时，给定初始容量
     * 5. 多线程中使用不安全，适合在单线程访问时使用，效率较高
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
