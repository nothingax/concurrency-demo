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
public class ThreadPoolExam3 {

    public static void main(String[] args) {

        // newSingleThreadExecutor 只用一个队列的线程池，可用于保证任务顺序执行
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                log.info("线程数：{}", index);
            });
        }
        executorService.shutdown();
    }
}
