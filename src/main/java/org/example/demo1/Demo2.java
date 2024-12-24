package org.example.demo1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo2 {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();

        new Thread(() -> {
            for (int i = 1; i <= 40; i++) ticket.saleTicket();
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 40; i++) ticket.saleTicket();
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 40; i++) ticket.saleTicket();
        }, "C").start();
    }
}

class Ticket2 {
    private Lock lock = new ReentrantLock();
    private int number = 30;

    public void saleTicket() {
        lock.lock();
        try {
            if (number > 0)
                System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "票，还剩:" + number);
        } finally {
            lock.unlock();
        }
    }
}
