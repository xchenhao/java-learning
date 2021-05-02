package com.test.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * StreamDemo 接口：不是存储的数据结构，数据源可以是一个集合。为了函数式编程
 * 惰式执行，数据只能被消费一次
 *
 * 两种类型的操作方法：
 * 1. 中间操作：生成一个 Stream
 * 2. 结束操作：执行计算操作
 */
public class StreamDemo {
    public static void main(String[] args) {

    }

    @Test
    public void foreach()
    {
        Stream<String> stream = Stream.of("good", "good", "study", "day", "day", "up");
        // foreach
        stream.forEach(System.out::println);
    }

    @Test
    public void filter()
    {
        Stream<String> stream = Stream.of("good", "good", "study", "day", "day", "up");
        // filter
        stream.filter((s) -> s.length() > 3).forEach(System.out::println);
    }

    @Test
    public void distinct()
    {
        Stream<String> stream = Stream.of("good", "good", "study", "day", "day", "up");
        // distinct
        stream.distinct().forEach(System.out::println);
    }

    @Test
    public void map()
    {
        Stream<String> stream = Stream.of("good", "good", "study", "day", "day", "up");
        // map
        stream.map(s -> s.toUpperCase()).forEach(System.out::println);
    }

    @Test
    public void flatMap()
    {
        Stream<List<Integer>> ss = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5));
        ss.flatMap(list -> list.stream()).forEach(System.out::println);
    }

    @Test
    public void reduce()
    {
        Stream<String> stream = Stream.of("good", "good", "study", "day", "day", "up");
        Optional<String> opt = stream.reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2);
        System.out.println(opt.get());
    }

    @Test
    public void collect()
    {
        Stream<String> stream = Stream.of("good", "good", "study", "day", "day", "up");
        List<String> list = stream.collect(Collectors.toList());
       // list.stream();
        list.forEach(System.out::println);

        // System.out::println
        // :: 方法引用
        // 引用静态方法  Integer::valueOf
        // 引用对象中的方法  list::add
        // 引用构造方法  ArrayList::new
    }

}
