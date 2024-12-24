package org.example.demo1;

public class Demo1 {
    public static void main(String[] args) {
        Ticket1 ticket = new Ticket1();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.saleTicket();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.saleTicket();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.saleTicket();
                }
            }
        }, "C").start();
    }
}

class Ticket1 {
    private int number = 30;

    public synchronized void saleTicket() {
        if (number > 0)
            System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "票，还剩:" + number);
    }
}