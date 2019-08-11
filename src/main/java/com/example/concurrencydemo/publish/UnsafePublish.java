package com.example.concurrencydemo.publish;

import com.example.concurrencydemo.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description:
 * 溢出：错误的发布，对象完成构造之前，就使他被其他线程可见。
 *
 * 溢出演示：一个类A，有一个内部类B，A构造方法中调用了B的构造方法，B的构造方法中使用了A的示例this。
 * 这个设计是存在问题的，A未构造完成之前就调用A的this
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/4/14 11:04 PM
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a", "b", "c"};

    public String[] getStages() {
        return states;
    }


    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        System.out.println(Arrays.toString(unsafePublish.getStages()));

        unsafePublish.getStages()[1] = "d";
        System.out.println(Arrays.toString(unsafePublish.getStages()));

    }
}
