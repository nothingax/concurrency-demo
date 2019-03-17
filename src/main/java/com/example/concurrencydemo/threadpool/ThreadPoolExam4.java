package com.example.concurrencydemo.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
public class ThreadPoolExam4 {

    public static void main(String[] args) {

        // newSingleThreadExecutor 只用一个队列的线程池，可用于保证任务顺序执行
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);


        // scheduledExecutorService.schedule(() -> {
        //     log.info("定时执行：======");
        // }, 3, TimeUnit.SECONDS);
        //
        // scheduledExecutorService.shutdown();

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.info("定时执行,间隔重复执行：======");
        }, 1, 3, TimeUnit.SECONDS);

    }
}
