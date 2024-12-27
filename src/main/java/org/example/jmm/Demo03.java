package org.example.jmm;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo03 {
    public static void main(String[] args) {
        // AtomicInteger 默认为 0
        AtomicInteger atomicInteger = new AtomicInteger(5);

        // compareAndSet   CAS 比较并交换
        // public final boolean compareAndSet(int expect, int update)
        // 如果这个值是期望的值，那么则更新为指定的值
        System.out.println(atomicInteger.compareAndSet(5, 20));
        // 如果这个值是期望的值，那么则更新为指定的值
        System.out.println(atomicInteger.compareAndSet(20, 5));

        // 数据都被动了！操作数据库记录！

        // 小明
        System.out.println(atomicInteger.compareAndSet(5, 100));

    }
}
