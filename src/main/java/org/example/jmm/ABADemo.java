package org.example.jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
    static AtomicStampedReference<Integer> atomicReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {

        // 其他人员 小花，需要每次执行完毕 + 1
        new Thread(() -> {
            int stamp = atomicReference.getStamp();// 获得版本号
            System.out.println("T1 stamp01=>" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicReference.compareAndSet(100, 101, atomicReference.getStamp(), atomicReference.getStamp() + 1);
            System.out.println("T1 stamp02=>" + atomicReference.getStamp());

            atomicReference.compareAndSet(101, 100, atomicReference.getStamp(), atomicReference.getStamp() + 1);
            System.out.println("T1 stamp03=>" + atomicReference.getStamp());
        }, "T1").start();


        // 乐观的小明
        new Thread(() -> {
            int stamp = atomicReference.getStamp();// 获得版本号
            System.out.println("T2 stamp01=>" + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = atomicReference.compareAndSet(100, 1, stamp, stamp + 1);
            System.out.println("T2 是否修改成功：" + result);
            System.out.println("T2 stamp02=>" + atomicReference.getStamp());
            System.out.println("T2 当前获取得最新的值=>" + atomicReference.getReference());

        }, "T2").start();

    }
}
