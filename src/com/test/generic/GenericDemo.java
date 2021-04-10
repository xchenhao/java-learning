package com.test.generic;

import org.junit.Test;

import java.util.*;

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

    @Test
    public void test2() {
        Node<Number> n1 = new Node<>(10);
        Node<Integer> n2 = new Node<>(20);
        getData(n1);
        // getData(n2); 不支持
        // n1 = n2; 不支持
        getData2(n1);
        getData2(n2);
    }

    public static void getData(Node<Number> node) {
        System.out.println(node.getData());
    }

    /**
     * 使用通配符定义泛型的类型，此时只能输出，不能修改
     * @param node
     */
    public static void getData2(Node<?> node) {
        System.out.println(node.getData());
    }

    @Test
    public void test3() {
        Node<Number> n1 = new Node<>(10);
        Node<Integer> n2 = new Node<>(20);

        getUpperNumberData(n1);
        getUpperNumberData(n2);
    }

    // 上限
    public static void getUpperNumberData(Node<? extends Number> data) {
        // 只能是 Number 类及其子类
        System.out.println("data: " + data.getData());
        // data.setData("30"); 也不能修改
    }

    @Test
    public void test4() {
        String[] arrays = {"vince", "zack", "tom", "lily"};
        String[] strs = func(arrays, 1, 3);
        System.out.println(Arrays.toString(arrays));
    }

    /**
     * 泛型方法
     * @param array
     * @param i
     * @param t
     * @param <T>
     * @return
     */
    public static<T> T[] func(T[] array, int i, int t) {
        T temp = array[i];
        array[i] = array[t];
        array[t] = temp;
        return array;
    }

    // 泛型嵌套
    @Test
    public void test5() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "vince");
        map.put(2, "tom");

        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for (Map.Entry entry: entries) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }
}
