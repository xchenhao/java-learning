package com.test.enumerate;

import org.junit.Test;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;

public class EnumDemo {

    public static final int RED = 0x1;
    public static final int GREEN = 0x2;
    public static final int BLUE = 0x3;

    public int color;

    @Test
    public void test1() {
        color = RED;
        // color = 4; 限制不了
    }

    public Color colorEnum;
    @Test
    public void test2() {
        colorEnum = Color.RED;
        // colorEnum = 4; 不能取枚举之外的值
        System.out.println(colorEnum);

        System.out.println(colorEnum.name());
        System.out.println(colorEnum.ordinal());  // 序号 0
        System.out.println(colorEnum.toString());

        Color[] values = Color.values();
        System.out.println(Arrays.toString(values));
    }

    @Test
    public void test3() {
        EnumSet<Color> set = EnumSet.allOf(Color.class);
        for (Color c: set) {
            System.out.println(c);
        }

        EnumMap<Color,String> enumMap = new EnumMap<>(Color.class);
        enumMap.put(Color.RED, "red");
        enumMap.put(Color.GREEN, "green");
        enumMap.put(Color.BLUE, "blue");
    }
}
