package com.example.concurrencydemo.deadlock;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description: 死锁，两个线程A和B，两个对象X和Y。
 * 两个线程各锁一各对象然后执行，执行内部的逻辑需要获取另一个对象的锁
 * 两个线程不放弃现在的锁，就无法拿到对方手里的锁
 * 因此导致死锁。
 *
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/4/13 12:45 PM
 */
public class DeadLock implements Runnable{
    private static Object o1 = new Object(), o2 = new Object();

    private int flag = 0;

    private DeadLock(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (this.flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("flag == 1 执行");
                }

            }

        } else if (this.flag == 0) {

            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("flag == 0 执行");
                }

            }
        }

    }


    public static void main(String[] args) {

        DeadLock deadLock1 = new DeadLock(1);
        DeadLock deadLock2 = new DeadLock(0);

        new Thread(deadLock1).start();
        new Thread(deadLock2).start();

    }
}
