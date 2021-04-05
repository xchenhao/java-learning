package com.test.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Documented  // 用于生成文档
@Retention(RetentionPolicy.RUNTIME)  // 表示该注解的作用范围在运行时存在
@Target(ElementType.TYPE)  // 注解的使用范围（类型、方法、属性、构造器、参数、局部变量、包、Annotation）
@Inherited // 允许子类继承该注解
public @interface MyAnnotation {
    // 定义变量
    public String name();
    public int age() default 1;  // 给变量设置默认值

    public String[] like(); // 定义一个数组变量

    public Color color();  // 定义一个枚举类型的变量

}
