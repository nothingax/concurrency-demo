package com.example.concurrencydemo.publish.singleton;

import com.example.concurrencydemo.annotation.ThreadSafe;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description:
 * 枚举方式的单例模式
 * 比懒汉模式更易保证线程安全（代码量少、jvm加载类时保证）、不存在恶汉模式的问题
 *
 *
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 */
@ThreadSafe
public class SingletonExample7 {

    /**
     * 私有化构造函数
     */
    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    /**
     * 内部枚举类
     * 原理，枚举在使用的时候进行加载
     */
    public enum Singleton {
        INSTANCE;
        private SingletonExample7 singleton;

        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }


}
