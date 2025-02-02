package org.example.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 两个静态同步方法，同一个手机请问先执行sendEmail 还是 sendSMS
 *
 * 答案：sendEmail
 * 只要方法被 static 修饰，锁的对象就是 Class模板对象,这个则全局唯一！所以说这里是同一个锁
 * 并不是因为synchronized
 */
public class Lock5 {
    public static void main(String[] args) throws InterruptedException {
        Phone5 phone = new Phone5();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            phone.sendSMS();
        }, "B").start();
    }
}

class Phone5 {
    public static synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendEmail");
    }

    public static synchronized void sendSMS() {
        System.out.println("sendSMS");
    }
}