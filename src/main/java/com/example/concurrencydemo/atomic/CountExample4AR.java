package com.example.concurrencydemo.atomic;


import com.example.concurrencydemo.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.awt.geom.PathIterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

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
public class CountExample4AR {

    static private AtomicReference<Integer> count = new AtomicReference<>();

    public static void main(String[] args) {
        count.compareAndSet(0, 2);
        count.compareAndSet(0, 1);
        count.compareAndSet(2, 4);
        count.compareAndSet(3, 5);
        count.compareAndSet(4, 6);
        log.info("{}",count);
    }

}
