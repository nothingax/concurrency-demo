package com.example.concurrencydemo.publish.singleton;

import com.example.concurrencydemo.annotation.ThreadSafe;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description:
 * <p>
 * 饿汉模式，对象的初始位置：static 和 私有属性上
 * <p>
 * 饿汉模式
 * 1、构造函数中不能有太多的逻辑、耗时逻辑，否则会使加载变慢。
 * 2、保证这个单例一定会用到，否则就是资源浪费
 *
 *
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 */
@ThreadSafe
public class SingletonExample6 {

    /**
     * 私有化构造函数
     */
    private SingletonExample6() {
    }

    /**
     * 私有 静态
     * 单例对象
     */
    private static SingletonExample6 instance = null;

    // 这个代码块要在 SingletonExample6 instance = null; 之后
    // 否则会 被定义方法给覆盖
    static {
        instance = new SingletonExample6();
    }


    /**
     * @return
     */
    public static SingletonExample6 getInstance() {
        return instance;
    }
}
