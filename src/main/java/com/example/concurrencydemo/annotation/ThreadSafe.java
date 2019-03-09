package com.example.concurrencydemo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Program Name: concurrency-demo
 * <p>
 * Description: 【线程安全】仅仅标识作用
 * <p>
 *
 * @author zhangjianwei
 * @version 1.0
 * @date 2019/3/9 7:49 PM
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {
    String value() default "";
}
