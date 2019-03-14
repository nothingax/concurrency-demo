package com.example.concurrencydemo.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description: 获取和释放多个许可
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/3/14 6:00 PM
 */

@Slf4j
public class Semaphore02 {

    private static int threadCount = 20;
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i <threadCount ; i++) {
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire(3); // 每次获取多个许可，此时容器内大于等于三个许可才能成功，否则在此阻塞。
                                                    // 在该程序执行过程中，信号量的剩余数目在3 2 3 2 3 2交替出现。
                    log.info("acquire:----------");
                    test(threadNum);
                    log.info("release:----------");
                    semaphore.release(3); // 每次释放多个许可
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
