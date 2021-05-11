package com.test.collections;

import org.junit.Test;

import java.util.*;

/**
 * Map 接口：
 * 1. 键值对存储一组对象
 * 2. Key 不能重复（唯一），Value 可以重复
 * 3. 具体的实现类：HashMap, TreeMap, HashTable, LinkedHashMap
 * 4. HashMap 与 HashTable 的区别？
 */
public class MapDemo {

    /**
     * LinkedHashMap 是 HashMap 的子类，由于 HashMap 不能保证顺序恒久不变，此类使用双重链表来维护
     *   元素添加的顺序
     */
    @Test
    public void LinkedHashMap()
    {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("one", "Lily");
        map.put("two", "Tom");
        map.put("three", "Jack");

        map.forEach((key, value) -> {System.out.println(key + "->" + value);});
    }

    /**
     * JDK 1.0 开始
     * 基于哈希表实现（数组+链表）
     * 默认数组大小为 11，加载因子为 0.75
     * 扩充方式：(原数组大小 << 1) + 1
     * 线程安全的，用在多线程访问时
     */
    @Test
    public void HashTable()
    {
        Map<String, String> table = new Hashtable<>();
        table.put("one", "Lily");
        table.put("two", "Tom");
        table.put("three", "Jack");

        table.forEach((key, value) -> {System.out.println(key + "->" + value);});
    }


    /**
     * HashMap 的实现原理：
     * 1. 基于哈希表（数组+链表+二叉树（红黑树）） JDK 1.8
     * 2. 默认加载因为为 0.75，默认数组大小为 16
     * 3. 把对象存储到哈希表中，如何存储？
     *    把 key 对象通过 hash() 方法计算 hash 值，然后用这个 hash 值对数组长度取余数（默认 16），来决定该 key 对象
     * 在数组中存储的位置，当这个位置有多个对象时，以链表结构存储，在 JDK 1.8 后，当链表长度大于 8 时，链表将转换为红黑树结构存储
     * 这样的目的，是为了取值时更快，存储的数据量越大，性能的表现越明显
     *
     * 4. 扩充原理：当数组的容量超过了 75%，那么表示该数组需要扩充，如何扩充？
     * 扩充的算法：当前数组容量 << 1（相当于是乘于 2），扩大 1 倍，扩充次数过多，会影响性能，每次扩充表示哈希表重新
     *   散列（重新计算每个对象的存储位置），我们在开发中尽量要减少扩充次数带来的性能问题。
     *
     * 5. 线程不安全，适合在单线程中使用
     *
     */
    @Test
    public void hashMap()
    {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Tom");
        map.put(2, "Jack");
        map.put(3, "Jack");
        map.put(4, "Bob");
        map.put(4, "Mary");

        System.out.println("map size: "+map.size());

        // 从 Map 中取值
        System.out.println(map.get(4));

        // Map 的遍历
        // entrySet（遍历 entry）
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for (Map.Entry entry:
             entries) {
            System.out.println(entry.getKey() + "|" + entry.getValue());
        }

        // keySet（遍历键）
        Set<Integer> keys = map.keySet();
        for (Integer i:
             keys) {
            String value = map.get(i);
            System.out.println(i + "-" + value);
        }

        // values（遍历值）
        Collection<String> values = map.values();
        for (String s :
                values) {
            System.out.println("value: "+s);
        }

        // forEach
        map.forEach((k, v) -> System.out.println(k + "+" + v));

        System.out.println(map.containsKey(7) + "  " + map.containsValue("Bob"));

        // hash
        Integer key = 1434;
        int h;
        System.out.println((h = key.hashCode()) ^ (h >>> 16));
        System.out.println(1434 & 15);
        System.out.println(1434 % 16);
    }

    public static void main(String[] args) {

    }

}
