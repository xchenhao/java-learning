package com.test.reflection.proxy;

import org.junit.Test;

public class TestDemo {

    // 动态代理
    @Test
    public void testProxy() {
        CreateProxy cp = new CreateProxy();   // 用来创建代理对象的对象

        Subject person = new Person();
        Subject proxy = (Subject)cp.create(person);
        proxy.shopping();  // invoke

        Hotel person2 = new Person();
        Hotel proxy2 = (Hotel)cp.create(person2);
        proxy2.reserve();  // invoke

    }

}
