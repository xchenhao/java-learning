package com.test.collections;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Map 接口：
 * 1. 键值对存储一组对象
 * 2. Key 不能重复（唯一），Value 可以重复
 * 3. 具体的实现类：HashMap, TreeMap, HashTable, LinkedHashMap
 */
public class MapDemo {

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
    }

    public static void main(String[] args) {

    }

}
