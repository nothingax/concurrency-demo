package com.example.concurrencydemo.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
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
public class ThreadPoolExam1 {

    public static void main(String[] args) {

        // newCachedThreadPool 可缓存的线程池，
        // 可定时回收空闲线程，没有可用可回收线程时将创建新的线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                log.info("线程数：{}", index);
            });
        }
        executorService.shutdown();
    }
}
