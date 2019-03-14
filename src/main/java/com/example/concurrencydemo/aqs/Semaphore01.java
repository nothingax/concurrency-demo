package com.example.concurrencydemo.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description: Semaphore信号量
 * 初始化一个值：线程的最大容量
 * 获取与释放1个许可
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/3/14 5:45 PM
 */
@Slf4j
public class Semaphore01 {

    private static final int threadCount = 20;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 初始化，semaphore如果看做一个容器，该初始值则为容器的最大容量
        Semaphore semaphore = new Semaphore(3);
        // 循环调用调用线程池，执行方法
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire(); // 获取【一个】许可
                    test(threadNum);
                    semaphore.release(); // 释放【一个】许可
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
            });
        }

        executorService.shutdown();
    }

    private static void  test(int threadNum ) throws InterruptedException {
        log.info("threadNum :" + threadNum);
        Thread.sleep(1000);
    }
}
