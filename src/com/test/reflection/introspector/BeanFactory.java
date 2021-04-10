package com.test.reflection.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 通过内省的 API 来装配一个 Bean 对象，Bean 对象的值是通过配置文件中来获取
 * 目的是为了提高维护性
 */
public class BeanFactory {
    private static Properties properties = new Properties();

    // 使用静态代码块读取配置文件
    static {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().
                getResourceAsStream("com/test/reflection/introspector/config.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取一个 Bean
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        Object bean = null;
        String beanName = properties.getProperty(name);
        try {
            Class<?> aClass = Class.forName(beanName);
            try {
                bean = aClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            try {
                // 通过类信息获取 Bean 的描述信息
                BeanInfo beanInfo = Introspector.getBeanInfo(aClass);
                // 通过 Bean 描述信息获取该类的所有属性描述器
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (int i = 0; i < propertyDescriptors.length; i++) {
                    String propertyName = propertyDescriptors[i].getName();
                    Method writeMethod = propertyDescriptors[i].getWriteMethod();
                    try {
                        if ("username".equals(propertyName)) {
                            // 调用属性的 set 方法
                            writeMethod.invoke(bean, properties.getProperty("bean.username"));
                        } else if ("password".equals(propertyName)) {
                            writeMethod.invoke(bean, properties.getProperty("bean.password"));
                        } else if ("url".equals(propertyName)) {
                            writeMethod.invoke(bean, properties.getProperty("bean.url"));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return bean;
    }
}
