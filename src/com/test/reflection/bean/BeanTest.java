package com.test.reflection.bean;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class BeanTest {
    // http://commons.apache.org/
    // http://commons.apache.org/proper/commons-beanutils/
    // http://commons.apache.org/proper/commons-logging/download_logging.cgi

    @Test
    public void test() {
        // 从客户端获取到的数据是这样
        String name = "bin";
        String age = "18";
        String salary = "20000";

        Emp emp = new Emp();

        try {
            BeanUtils.setProperty(emp, "name", name);
            BeanUtils.setProperty(emp, "age", age);
            BeanUtils.setProperty(emp, "salary", salary);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(emp.getInfo());
    }
}
