package com.test.thread;

/**
 * join 方法：
 * 加入线程，让调用的线程先执行指定时间或执行完毕
 */
public class ThreadDemo2 {

    public static void main(String[] args) {
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
                /*
                try {
                    t.join(); // 让 t 线程执行完毕，主线程才继续执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                */
                t.interrupt(); // 中断线程，只是做了一个中断标记
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
