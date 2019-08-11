package com.example.concurrencydemo.atomic;


import com.example.concurrencydemo.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description:
 * LongAdder :热点数据分离
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/3/9 8:57 PM
 */

@Slf4j
@ThreadSafe
public class CountExample6AtomicBoolean {

    /**
     * 常用于限制一段逻辑只执行一次
     */
    static private AtomicBoolean hasHappen = new AtomicBoolean(false);
    private static int threadTotal = 200;
    // 数值设置的大一些，更容易看出问题
    private static int clientTotal = 100000;

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
                    testAtomicBoolean();
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
    }

    private static void testAtomicBoolean() {
        if (hasHappen.compareAndSet(false, true)) {
            log.info("execute：{}",hasHappen);
        }
    }
}
