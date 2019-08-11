package com.example.concurrencydemo.publish.singleton;

import com.example.concurrencydemo.annotation.ThreadSafe;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description:
 * 饿汉模式
 * 1、构造函数中不能有太多的逻辑、耗时逻辑，否则会使加载变慢。
 * 2、保证这个单例一定会用到，否则就是资源浪费
 *
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 */
@ThreadSafe
public class SingletonExample2 {

    /**
     * 私有化构造函数
     */
    private SingletonExample2() {
    }

    /**
     * 私有 静态
     * 单例对象
     */
    private static SingletonExample2 instance = new SingletonExample2();

    /**
     * @return
     */
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
