package org.example.fuzhu;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6); // 默认初始值为6
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " Start");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();// 只要计数器没有归零，一直阻塞

        System.out.println(Thread.currentThread().getName() + " End");
    }
}
