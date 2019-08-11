package com.example.concurrencydemo.publish.singleton;

import com.example.concurrencydemo.annotation.ThreadSafe;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description:
 * 懒汉模式
 * 对 SingletonExample4的优化
 * 解决指令重排序的问题
 *
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 */
@ThreadSafe
public class SingletonExample5 {

    /**
     * 私有化构造函数
     */
    private SingletonExample5() {
    }
    
    /**
     * 私有 静态
     * 单例对象
     *
     * volatile 用来禁止指令重排
     */
    private static volatile SingletonExample5 instance = null;

    /**
     * 对 SingletonExample3的优化，此方法的访问是线程安全的
     * 双从检测+同步锁
     *
     * 仍然存在的问题：指令重排序对A B 两步骤存在的影响
     * 指令重排发生的原因是jvm和cpu优化
     *
     *
     * 正常排序
     *     // 1、memory = allocate() 分配对象的内存空间
     *     // 2、ctorInstance() 初始化对象
     *     // 3、instance = memory 设置instance指向刚分配的内存
     *
     * 指令重排后 可能变为
     *
     * 1 3 2
     * 影响点在 步骤A，可能在 instance = memory 执行后，判断步骤A为ture，但真正使用的时候报错，
     * 因为ctorInstance() 初始化对象 未执行。
     *
     */
    public static SingletonExample5 getInstance() {
        A : if (instance == null) {
            synchronized (SingletonExample5.class) {
          B :     if (instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
