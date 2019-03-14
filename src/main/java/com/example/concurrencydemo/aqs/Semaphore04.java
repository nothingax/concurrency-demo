package com.example.concurrencydemo.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description: 在一段时间内尝试获取许可
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/3/14 6:00 PM
 */

@Slf4j
public class Semaphore04 {

    private static int threadCount = 20;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i <threadCount ; i++) {
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    // 在一段时间内尝试获取许可,超出此时间不再获取，此代码总将会获取 3 * 5 = 15个
                    if (semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) {
                        test(threadNum);
                        semaphore.release();
                    }
                } catch (InterruptedException e) {
                    log.info("exception:", e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("threadNum:{}", threadNum);
        Thread.sleep(1000);
    }
}
