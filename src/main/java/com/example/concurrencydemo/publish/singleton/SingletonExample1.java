package com.example.concurrencydemo.publish.singleton;

import com.example.concurrencydemo.annotation.NotThreadSafe;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description:
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * 线程不安全
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 */
@NotThreadSafe
public class SingletonExample1 {

    /**
     * 私有化构造函数
     */
    private SingletonExample1() {
    }


    /**
     * 私有 静态
     */
    private static SingletonExample1 instance = null;

    /**
     * 多个线程并发访问此方法，并不安全
     * @return
     */
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
