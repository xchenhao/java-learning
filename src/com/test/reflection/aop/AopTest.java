package com.test.reflection.aop;

import org.junit.Test;

import java.io.InputStream;

public class AopTest {

    @Test
    public void test() {
        // 1 读取配置文件
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().
                getResourceAsStream("com/test/reflection/aop/bean.properties");
        // 2 创建 Bean 工厂类
        BeanFactory beanFactory = new BeanFactory(resourceAsStream);

        // 3 获取代理对象
        ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean)beanFactory.getBean("bean");
        IManager proxy = (IManager)proxyFactoryBean.getProxy();
        proxy.add("Tom");

    }
}
