package com.example.concurrencydemo.publish.singleton;

import com.example.concurrencydemo.annotation.ThreadSafe;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description:
 * 懒汉模式
 *
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 */
@ThreadSafe
public class SingletonExample3 {

    /**
     * 私有化构造函数
     */
    private SingletonExample3() {
    }
    
    /**
     * 私有 静态
     * 单例对象
     */
    private static SingletonExample3 instance = null;

    /**
     * 对 SingletonExample1的优化，此方法的访问是线程安全的
     * 但引发新的问题是，使性能变差
     *
     */
    public synchronized static SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
