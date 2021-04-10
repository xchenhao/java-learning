package com.test.reflection;

import org.junit.Test;

import java.lang.reflect.*;

public class ReflectionDemo {
    /**
     * 获取 Class 对象的三种形式
     */
    @Test
    public void test() {
        Dog dog = new Dog("wangwang", 3, "black");
 
        // 通过对象的 getClass() 方法
        // Class<? extends Dog> aClass = dog.getClass();
        Class aClass = dog.getClass();

        // 通过类.class
        // Class<Dog> dogClass = Dog.class;
        Class dogClass = Dog.class;

        // 通过 Class.forName 方法
        try {
            // Class<?> aClass1 = Class.forName("com.test.reflection.Dog");
            Class aClass1 = Class.forName("com.test.reflection.Dog");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射来实例化对象
     */
    @Test
    public void test2() {
        Class<Dog> dogClass = Dog.class;
        try {
            // 通过 Class 对象实例化类对象，调用了默认的无参的构造方法
           
            Dog dog = (Dog)dogClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        Class<Dog> dogClass = Dog.class;
        // 获取所有的构造方法
        Constructor<?>[] constructors = dogClass.getConstructors();
        for (int i = 0; i < constructors.length; i++) {
            System.out.println(constructors[i].getName());
            System.out.println(constructors[i].getParameterCount());
        }

        try {
            // 获取一个指定的构造方法
            Constructor<Dog> constructor = dogClass.getConstructor(String.class, int.class, String.class);
            try {
                // 调用带参数的构造器来实例化对象
                Dog dog = constructor.newInstance("小白", 5, "白色");

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    // 获取所有的属性
    @Test
    public void test4() {
        Class<Dog> dogClass = Dog.class;
        Field[] fields = dogClass.getFields();  // 获取所有非私有属性
        System.out.println(fields.length);      // 1

        Field[] declaredFields = dogClass.getDeclaredFields(); // 获取所有属性（包括私有属性）
        System.out.println(declaredFields.length);      // 4
        for (int i = 0; i < declaredFields.length; i++) {
            int modifiers = declaredFields[i].getModifiers();
            System.out.println(Modifier.toString(modifiers) + "|" + declaredFields[i].getType() + "|" + declaredFields[i].getName());
        }
    }

    @Test
    public void test5() {
        Class<Dog> dogClass = Dog.class;
        Package aPackage = dogClass.getPackage();
        System.out.println(aPackage.getName()); // 获取包名

        Dog dog = new Dog("wangwang", 4, "白色");

        Method[] methods = dogClass.getMethods();  // 获取公共的方法（包括继承的公有方法）
        for (int i = 0; i < methods.length; i++) {
            System.out.println(methods[i]);
            if (methods[i].getName().equals("toString")) {
                try {
                    String s = (String)methods[i].invoke(dog);
                    System.out.println(s);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("--------------------------------------");
        // 访问私有方法（获取到本类中定义的所有方法，不包括父类的）
        Method[] declaredMethods = dogClass.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            System.out.println(declaredMethods[i].getName());

            if (declaredMethods[i].getName().equals("set")) {
                // 设置私有方法可以被访问（去除访问修饰符的检查）
                declaredMethods[i].setAccessible(true);  // 不设置的话：java.lang.IllegalAccessException: cannot access a member of class com.test.reflection.Dog with modifiers "private"

                try {
                    declaredMethods[i].invoke(dog);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
