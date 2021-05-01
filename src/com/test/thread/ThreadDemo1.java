package com.test.thread;

/**
 * 线程的休眠
 * 在当前线程的执行中，暂停指定的毫秒数，释放 CPU 的时间片
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
        MyThread mt = new MyThread();
        mt.start();  // 启动线程

        MyRunnable mr = new MyRunnable();  // 推荐（把 MyRunnable 当做一种任务，可以放到不同的 Thread 中）
        Thread t = new Thread(mr);
        t.start();
    }
}

class MyThread extends Thread {
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

class MyRunnable implements Runnable {
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
