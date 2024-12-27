package org.example.jmm;

import java.util.concurrent.TimeUnit;

public class Demo01 {

    private volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (num == 0) { // 没有加 volatile 的时候，这个对象不可见

            }
        }).start();

        TimeUnit.SECONDS.sleep(1);
        num = 1;
        System.out.println(num);
    }

}
