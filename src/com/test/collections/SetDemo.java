package com.test.collections;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Set 接口：
 * 1. 无序（不保证顺序）
 * 2. 不允许重复元素
 * HashSet, TreeSet, LinkedHashSet
 *
 * 如果要排序，选择 TreeSet
 * 如果不要排序，也不用保证顺序选择 HashSet
 * 如果不要排序，要保证顺序选择 LinkedHashSet
 */
public class SetDemo {

    /**
     * 哈希表和链表的实现
     * 维护着一个运行于所有条目的双重链接列表，此链接列表定义了迭代顺序，即按照将元素插入到 set 中的顺序（插入顺序）进行迭代
     */
    @Test
    public void linkedHashSet()
    {
        LinkedHashSet<Cat> set = new LinkedHashSet<>();

        Cat c1 = new Cat("喵喵 1", 5, 1);
        Cat c2 = new Cat("喵喵 2", 2, 2);
        Cat c3 = new Cat("喵喵 3", 5, 3);
        Cat c4 = new Cat("喵喵 4", 5, 1);

        set.add(c1);
        set.add(c2);
        set.add(c3);
        set.add(c4);

        for (Cat c :
                set) {
            System.out.println(c);
        }
    }

    /**
     * 有序的 Set
     * 基于 TreeMap（二叉树数据结构），对象需要比较大小，通过对象比较器来实现
     * 对象比较器还可以用来去除重复元素，如果自定义的数据类没有实现比较器接口，将无法添加到 TreeSet 集合中
     */
    @Test
    public void treeSet()
    {
        TreeSet<Cat> tree = new TreeSet<>(new CatComparator());

        Cat c1 = new Cat("喵喵 1", 5, 1);
        Cat c2 = new Cat("喵喵 2", 2, 2);
        Cat c3 = new Cat("喵喵 3", 5, 3);
        Cat c4 = new Cat("喵喵 4", 5, 1);

        tree.add(c1);
        tree.add(c2);
        tree.add(c3);
        tree.add(c4);

        System.out.println(tree.size());  // 2（ 3 个 age=5 当做同一个对象）
        for (Cat c: tree) {
            System.out.println(c);
        }
    }


    /**
     * HashSet
     * 1. 实现原理：基于哈希表（HashMap）实现
     * 2. 不允许重复，可以有一个 Null 元素
     * 3. 不保证顺序恒久不变
     * 4. 添加元素时把元素作为 HashMap 的 key 存储，HashMap 的 value 使用一个固定的 Object
     * 5. 排除重复元素是通过 equals 来检查对象是否相同
     * 6. 判断两个对象是否相同：
     *   先判断两个对象的 hashcode 是否相同（相同不一定是同一个对象，可能两个对象生成的 hashcode 相同，如果不同，那一定不同同一个对象），
     *   如果相同，还要进行 equals 判断，相同的则为同一个对象
     * 7. 自定义对象要认为属性值都相同时，为同一个对象，这种需求时，那么我们要重写对象所在类的 hashCode 和 equals 方法
     *
     * 小结：
     * （1）哈希表的存储结构：数组+链表，数组里的每个元素以链表的形式存留
     * （2）如何把对象存储到哈希表中，先计算对象的 hashCode 值，再对数组的长度求余数，来决定对象要存储在数组中的哪个位置
     * （3）解决 HashSet 中的重复值使用的方式，见上述 6.
     */
    @Test
    public void hashSet()
    {
        // 哈希表：数组+链表的实现

        Set<String> set = new HashSet<>();
        set.add("吕布");
        set.add("刘备");
        set.add("关羽");
        set.add("曹操");
        set.add("曹操");

        for (String s: set) {
            System.out.println(s);
        }

        System.out.println("---------------->");
        String[] names = set.toArray(new String[]{});
        for (String s: names) {
            System.out.println(s);
        }


        Cat c1 = new Cat("喵喵 1", 5, 1);
        Cat c2 = new Cat("喵喵 2", 2, 2);
        Cat c3 = new Cat("喵喵 3", 5, 3);
        Cat c4 = new Cat("喵喵 1", 5, 1); // 属性与 c1 相同
        Set<Cat> cats = new HashSet<>();
        cats.add(c1);
        cats.add(c2);
        cats.add(c3);
        System.out.println(cats.size());  // 3

        cats.add(c4);
        System.out.println(cats.size());  // 4

        cats.add(c1);
        System.out.println(cats.size());  // 4

        for (Cat c: cats) {
            System.out.println(c);
        }
        System.out.println("c1: " + c1.hashCode() + ", " + c1.hashCode() % 16);
        System.out.println("c2: " + c2.hashCode() + ", " + c2.hashCode() % 16);
        System.out.println("c3: " + c3.hashCode() + ", " + c3.hashCode() % 16);
        System.out.println("c4: " + c4.hashCode() + ", " + c4.hashCode() % 16);
    }

}
