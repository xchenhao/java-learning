package com.test.collections;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 集合的迭代
 */
public class IteratorDemo {

    //
    @Test
    public void predicateTest()
    {
        List<String> list = Arrays.asList("Larry", "Moe", "Curly", "Tom", "Bob");
        List<String> data = filter(list, (s) -> s.contains("o"));
        data.forEach(System.out::println);
    }

    private static List<String> filter(List<String> list, Predicate<String> p)
    {
        List<String> result = new ArrayList<>();
        for (String str: list) {
            if (p.test(str)) {  // 测试是否符合要求
                result.add(str);
            }
        }

        return result;
    }

    // Supplier 代表结果供应商
    @Test
    public void supplierTest()
    {
        List<Integer> list = getNums(10, () -> (int)(Math.random() * 100));
        list.forEach(System.out::println);
    }

    public static List<Integer> getNums(int num, Supplier<Integer> supplier)
    {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }

        return list;
    }

    // Function 表示接收一个参数并产生一个结果的函数
    @Test
    public void functionTest()
    {
        // String s = strToUpper("tom", (s) -> s.toUpperCase());
        String s = strToUpper("tom", String::toUpperCase);
        System.out.println(s);
    }

    public static String strToUpper(String str, Function<String, String> f)
    {
        return f.apply(str);
    }

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
