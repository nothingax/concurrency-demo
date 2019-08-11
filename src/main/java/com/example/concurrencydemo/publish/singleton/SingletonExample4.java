package com.example.concurrencydemo.publish.singleton;

import com.example.concurrencydemo.annotation.ThreadSafe;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description:
 * 懒汉模式
 * 对 SingletonExample3的优化
 *
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 */
@ThreadSafe
public class SingletonExample4 {

    /**
     * 私有化构造函数
     */
    private SingletonExample4() {
    }
    
    /**
     * 私有 静态
     * 单例对象
     */
    private static SingletonExample4 instance = null;

    /**
     * 对 SingletonExample3的优化，此方法的访问是线程安全的
     * 双从检测+同步锁
     *
     * 仍然存在的问题：指令重排序对A B 两步骤存在的影响
     *
     *
     */
    public static SingletonExample4 getInstance() {
        A : if (instance == null) {
            // TODO synchronized (SingletonExample4.class) 有其他替换方法吗。比如将方法体换成一个private方法，进行加锁
            synchronized (SingletonExample4.class) {
          B :     if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
