package com.test.thread;

/**
 * 1. 多线程共享数据时，会发生线程不安全的情况
 * 2. 多线程共享数据时，必须使用同步
 */
public class ThreadDemo4 {

    public static void main(String[] args) {
        MyRunnable5 mr5 = new MyRunnable5();

        Thread t1 = new Thread(mr5);
        Thread t2 = new Thread(mr5);

        t1.start();
        t2.start();
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
