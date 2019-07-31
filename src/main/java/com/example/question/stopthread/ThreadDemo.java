package com.example.question.stopthread;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description: 退出线程的方法，线程对象设置标志位
 * 参考 https://blog.csdn.net/xu__cg/article/details/52831127
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/7/31 2:02 PM
 */
public class ThreadDemo extends Thread {

    /**
     * TODO 退出线程标志位
     * 最好使用atomic下的布尔
     */
    public volatile boolean exit = false;
    public void run() {
        while (!exit){
            System.out.println("线程中的任务执行~~");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        // 退出方法1
        final ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadDemo.exit = true;
    }
}
