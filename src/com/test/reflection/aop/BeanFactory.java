package com.test.reflection.aop;

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
 * AOP 框架的简单实现
 */
public class BeanFactory {
    Properties prop = new Properties();

    public BeanFactory(InputStream in) {
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取一个 bean 实例
     * @param name
     * @return
     */
    public Object getBean(String name) {
        Object bean = null;

        String className = prop.getProperty(name);
        try {
            // 获取 ProxyFactoryBean 的 class 对象
            Class<?> aClass = Class.forName(className);
            try {
                bean = aClass.newInstance();  // 实例化对象

                // 根据配置文件实例化 target 和 advice 对象
                Object target = Class.forName(prop.getProperty(name + ".target")).newInstance();
                Object advice = Class.forName(prop.getProperty(name + ".advice")).newInstance();

                try {
                    // 通过内省实例对 ProxyFactoryBean 的属性赋值
                    BeanInfo beanInfo = Introspector.getBeanInfo(aClass);
                    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                    for (PropertyDescriptor pd: propertyDescriptors) {
                        String propertyName = pd.getName();
                        Method writeMethod = pd.getWriteMethod();
                        if ("target".equals(propertyName)) {
                            writeMethod.invoke(bean, target);
                        } else if ("advice".equals(propertyName)) {
                            writeMethod.invoke(bean, advice);
                        }
                    }
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return bean;
    }
}
