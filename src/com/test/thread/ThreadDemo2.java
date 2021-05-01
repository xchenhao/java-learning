package com.test.thread;

import org.junit.Test;

/**
 * join 方法：
 * 加入线程，让调用的线程先执行指定时间或执行完毕
 * 中断线程
 * （1）使用 interrupt 方法来中断线程，设置一个中断标记（很多方法都会清除这个标记，需要判断标记，再打上标记）
 * （2）使用自定义中断标记方式（推荐
 */
public class ThreadDemo2 {

    @Test
    public void main0() {
        MyRunnable2 mr = new MyRunnable2();
        Thread t = new Thread(mr);
        t.start();

        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 20) {
                try {
                    t.join(); // 让 t 线程执行完毕，主线程才继续执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void main1() {
        MyRunnable2 mr = new MyRunnable2();
        Thread t = new Thread(mr);
        t.start();

        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 20) {
                t.interrupt(); // 中断线程，只是做了一个中断标记
            }
        }
    }

    @Test
    public void main2()
    {
        MyRunnable3 mr = new MyRunnable3();

        Thread t2 = new Thread(mr);
        t2.start();

        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName() + "-" + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 20) {
                mr.flag = false;  // 自定义标记方式中断线程
            }
        }
    }
}

class MyRunnable2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            if (Thread.interrupted()) { // 判断中断状态，此方法会把中断标记清除掉
                // ... 中断的收尾工作
                break;
            }
            System.out.println(Thread.currentThread().getName() + "-" + i );
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {  // 会清除中断标记
                e.printStackTrace();
                Thread.currentThread().interrupt();  // 再加上标记
            }
        }
    }
}

class MyRunnable3 implements Runnable {
    public boolean flag = true;
    public MyRunnable3() {
        flag = true;
    }

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println(Thread.currentThread().getName() + "-" + (i++));
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
