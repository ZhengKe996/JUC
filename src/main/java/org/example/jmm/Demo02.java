package org.example.jmm;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo02 {
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add() {
        num.getAndIncrement(); // num++
//        num.getAndDecrement(); // --


    }

    public static void main(String[] args) {
        // 期望 num 最终是 2 万
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    add();
                }
            }, String.valueOf(i)).start();
        }

        // 判断活着的线程
        while (Thread.activeCount() > 2) { // mian  gc
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
