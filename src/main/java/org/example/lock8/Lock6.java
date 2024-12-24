package org.example.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 两个静态同步方法，两个手机，请问先执行sendEmail 还是 sendSMS
 * 答案：sendEmail
 * 只要方法被 static 修饰，锁的对象就是 Class模板对象,这个则全局唯一！所以说这里是同一个锁
 * 并不是因为synchronized
 */
public class Lock6 {
    public static void main(String[] args) throws InterruptedException {
        Phone6 phone1 = new Phone6();
        Phone6 phone2 = new Phone6();

        new Thread(() -> {
            try {
                phone1.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            phone2.sendSMS();
        }, "B").start();
    }
}

class Phone6 {

    public static synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendEmail");
    }

    public static synchronized void sendSMS() {
        System.out.println("sendSMS");
    }

}