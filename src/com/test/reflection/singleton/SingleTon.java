package com.test.reflection.singleton;

import java.io.Serializable;

/**
 * 单例模式
 * 1. 多线程访问的安全问题 synchronized
 * 2. 保证变量的一致性 volatile
 * 3. 防止反射私有化构造方法：throw
 * 4. 让单例类可以被序列化：Serializable
 */
public class SingleTon implements Serializable {
    private volatile static SingleTon singleTon = null;
    private SingleTon(){
        if (singleTon != null) {
            throw new RuntimeException("此类对象为单例模式，已经被实例化了");
        }
    }

    public static SingleTon getInstance() {
        if (singleTon ==  null) {  // 防止已经 new 过
            synchronized (SingleTon.class) {  // 同步
                if (singleTon == null) {
                    singleTon = new SingleTon();
                }
            }
        }

        return singleTon;
    }


}
