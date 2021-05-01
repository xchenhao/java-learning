package com.test.thread;

import org.junit.Test;

public class ThreadDemo3 {

    @Test
    public void test01()
    {
        MyRunnable4 mr = new MyRunnable4();
        Thread t = new Thread(mr);

        // 线程可以分成守护线程和用户线程
        // 当进程中没有用户线程时，JVM 会退出
        t.setDaemon(true);  // 把线程设置为守护线程
        t.setPriority(Thread.MAX_PRIORITY);  // 优先级高可以提高该线程抢占 CPU 时间片的概率大
        t.setName("Great-Thread");
        System.out.println(t.isAlive());  // false
        t.start(); // 等待 CPU 调试
        System.out.println(t.isAlive());  // start 之后 isAlive=true

        for (int i = 0; i < 50; i++) {
            System.out.println("main-" + i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 5) {
                Thread.yield();  // 出让本次 CPU 的时间片
            }
        }

    }
}

class MyRunnable4 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
