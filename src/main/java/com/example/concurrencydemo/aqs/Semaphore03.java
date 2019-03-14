package com.example.concurrencydemo.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description: 尝试获取许可
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/3/14 6:00 PM
 */

@Slf4j
public class Semaphore03 {

    private static int threadCount = 20;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i <threadCount ; i++) {
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    // 尝试获取许可,在该代码中，获取不到许可的线程直接跳过
                    if (semaphore.tryAcquire()) {
                        log.info("acquire:----------");
                        test(threadNum);
                        log.info("release:----------");
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
