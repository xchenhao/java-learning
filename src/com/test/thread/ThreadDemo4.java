package com.test.thread;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. 多线程共享数据时，会发生线程不安全的情况
 * 2. 多线程共享数据时，必须使用同步
 * 3. 实现同步的三种方法
 * （1）使用同步代码块
 * （2） 使用同步方法
 * （3）使用 Lock（更灵活的代码控制）
 * 多线程共享数据，会有安全问题，使用同步可以解决安全问题，但同时会牺牲性能，所以同步的代码块要尽量保持简洁，把不随数据变化的相关代码移除同步块
 * 不要阻塞
 */
public class ThreadDemo4 {

    @Test
    public void test01() {
        MyRunnable5 mr = new MyRunnable5();

        Thread t1 = new Thread(mr);
        Thread t2 = new Thread(mr);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        MyRunnable6 mr = new MyRunnable6();

        Thread t1 = new Thread(mr);
        Thread t2 = new Thread(mr);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test03() {
        MyRunnable7 mr = new MyRunnable7();

        Thread t1 = new Thread(mr);
        Thread t2 = new Thread(mr);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyRunnable5 implements Runnable {
    private int ticket = 10;  // 售票
    private Object obj = new Object();  // 同步锁

    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
         //   synchronized (obj) {
            synchronized (this) {  // 把当前对象作为锁
                if (ticket > 0) {
                    ticket--;
                    try {
                        Thread.sleep(1000);  // 不会丢失监视器的所有权
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("您购买的票已剩余：" + ticket + " 张");
                }
            }
        }
    }
}

class MyRunnable6 implements Runnable {
    private int ticket = 10;  // 售票

    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            method();
        }
    }

    // 同步方法
    // 同步的对象是当前对象 this
    private synchronized void method() {
        if (ticket > 0) {
            ticket--;
            try {
                Thread.sleep(1000);  // 不会丢失监视器的所有权
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("您购买的票已剩余：" + ticket + " 张");
        }
    }
}

class MyRunnable7 implements Runnable {
    private int ticket = 10;  // 售票

    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            method2();
        }
    }

    ReentrantLock lock = new ReentrantLock();  // 互斥锁

    // Lock 实现同步
    private void method2() {
        lock.lock();  // 锁

        try {
            if (ticket > 0) {
                ticket--;

                try {

                    Thread.sleep(1000);  // 不会丢失监视器的所有权
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("您购买的票已剩余：" + ticket + " 张");
            }
        } finally {
            lock.unlock();  // 释放锁
        }
    }
}
