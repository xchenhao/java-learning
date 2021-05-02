package com.test.collections;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * 集合的迭代
 */
public class IteratorDemo {

    /**
     * JDK 1.8 新的迭代方法
     */
    @Test
    public void foreach2()
    {
        List<String> list = new ArrayList<>();
        list.add("tom");
        list.add("jack");
        list.add("bob");
        list.add("lily");


        list.forEach(System.out::println);

        /*
        list.forEach((String s) -> {
            System.out.println(s);
        });

        list.forEach((s) -> {
            System.out.println(s);
        });

        list.forEach(s -> {
            System.out.println(s);
        });

        list.forEach( s -> System.out.println(s));
        */
    }

    @Test
    public void foreach()
    {
        List<Cat> list = new ArrayList<>();
        list.add(new Cat("A", 10, 1));
        list.add(new Cat("B", 20, 2));
        list.add(new Cat("C", 30, 3));
        list.add(new Cat("D", 40, 4));

        for (Cat cat: list) {  // JDK 1.5 引入 foreach
            System.out.println(cat);
        }
    }

    @Test
    public void iterator()
    {
        List<Cat> list = new ArrayList<>();
        list.add(new Cat("A", 10, 1));
        list.add(new Cat("B", 20, 2));
        list.add(new Cat("C", 30, 3));
        list.add(new Cat("D", 40, 4));

        Iterator<Cat> iterator = list.iterator();  // JDK 1.5 之前统一的迭代集合方式
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void enumeration()
    {
        Vector<String> vs = new Vector<>();
        vs.add("tom");
        vs.add("jack");
        vs.add("bob");

        Enumeration<String> elements = vs.elements();
        while (elements.hasMoreElements()) {
            System.out.println(elements.nextElement());
        }

        // ListIterator<String> 可向前、向后进行迭代
    }


}
