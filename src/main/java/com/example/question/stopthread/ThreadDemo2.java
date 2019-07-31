package com.example.question.stopthread;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description:
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/7/31 2:10 PM
 */
public class ThreadDemo2 extends Thread {
    public void run() {
        while (!isInterrupted()) { //非阻塞过程中通过判断中断标志来退出

            System.out.println("线程中的任务执行~~");
            try {
                Thread.sleep(5 * 100);//阻塞过程捕获中断异常来退出
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;//捕获到异常之后，执行break跳出循环。
            }
        }
    }


    public static void main(String[] args) {
        // 退出方法1
        Thread thread = new ThreadDemo2();
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }


    /*
    * 使用stop方法终止线程
程序中可以直接使用thread.stop()来强行终止线程，但是stop方法是很危险的，就象突然关闭计算机电源，
而不是按正常程序关机一样，可能会产生不可预料的结果，不安全主要是：thread.stop()调用之后，
创建子线程的线程就会抛出ThreadDeatherror的错误，并且会释放子线程所持有的所有锁。一般任何进行加锁的代码块，
都是为了保护数据的一致性，如果在调用thread.stop()后导致了该线程所持有的所有锁的突然释放(不可控制)，
那么被保护数据就有可能呈现不一致性，其他线程在使用这些被破坏的数据时，有可能导致一些很奇怪的应用程序错误。
因此，并不推荐使用stop方法来终止线程。*/
}
