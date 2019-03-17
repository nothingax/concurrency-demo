package com.example.concurrencydemo.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description:
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/3/17 6:38 PM
 */
@Slf4j
public class ThreadPoolExam2 {

    public static void main(String[] args) {

        // newFixedThreadPool 定长线程池，用于控制最大并发数
        // 超过的最大数量的线程将队列中等待
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                log.info("线程数：{}", index);
            });
        }
        executorService.shutdown();
    }
}
