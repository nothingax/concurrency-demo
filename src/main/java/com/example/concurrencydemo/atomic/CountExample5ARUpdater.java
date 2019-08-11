package com.example.concurrencydemo.atomic;


import com.example.concurrencydemo.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

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
public class CountExample5ARUpdater {

    /**
     * 原子性的更新某一个类中的字段，要求类中的字段要volatile修饰，且不能static
     */
    static private AtomicIntegerFieldUpdater<CountExample5ARUpdater> updater =
            AtomicIntegerFieldUpdater.newUpdater(CountExample5ARUpdater.class, "count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {
        CountExample5ARUpdater example5ARUpdater = new CountExample5ARUpdater();
        if (updater.compareAndSet(example5ARUpdater, 100, 200)) {
            log.info("update success 1 count = {}", example5ARUpdater.getCount());
        }

        if (updater.compareAndSet(example5ARUpdater, 100, 200)) {
            log.info("update success 2 count = {}", example5ARUpdater.getCount());
        } else {
            log.info("update failed  count = {}", example5ARUpdater.getCount());
        }

    }

}
