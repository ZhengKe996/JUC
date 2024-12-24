package org.example.demo1;

public class Dome3 {
    public static void main(String[] args) {
        Data data = new Data();

        new Thread(() -> {
            for (int i = 1; i <= 40; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 40; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}

class Data {
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        while (number != 0) this.wait();

        number++; // 执行
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll(); //唤醒所有线程
    }

    public synchronized void decrement() throws InterruptedException {
        while (number != 1) this.wait();

        number--; // 执行
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll(); //唤醒所有线程
    }

}