package com.example.concurrencydemo.atomic;


import com.example.concurrencydemo.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description:
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/3/9 8:57 PM
 */

@Slf4j
@ThreadSafe
public class CountExample1AtomicInteger {

    private static int threadTotal = 200;
    // 数值设置的大一些，更容易看出问题
    private static int clientTotal = 100000;

    private static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        // 信号量
        Semaphore semaphore = new Semaphore(threadTotal);
        // 计数器闭锁
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int index = 0; index < clientTotal; index++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("error", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        // 关闭线程池
        executorService.shutdown();
        log.info("计数完毕：count = {}", count);
    }

    private static void add() {
        // 重点在这里，看源码可以看到，增加操作是一个do while循环，
        // 在加值之前会判断现在的值是否和主存里的值相同，是的话再加，否则等下一次循环
        count.incrementAndGet();
    }
}
