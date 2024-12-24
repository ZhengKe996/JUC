package org.example.demo1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dome4 {
    public static void main(String[] args) {
        Data2 data = new Data2();
        new Thread(() -> {
            for (int i = 1; i <= 40; i++) data.print5();
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 40; i++) data.print10();
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 40; i++) data.print15();
        }, "C").start();
    }
}

class Data2 {
    private int number = 1; // 1A 2B  3C
    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while (number != 1) condition1.await();
            for (int i = 1; i <= 5; i++)
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (number != 2) condition1.await();
            for (int i = 1; i <= 5; i++)
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }


    public void print15() {
        lock.lock();
        try {
            while (number != 3) condition1.await();
            for (int i = 1; i <= 5; i++)
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}