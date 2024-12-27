package org.example.pool;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println("本机可用的线程数: =>" + Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(2, Runtime.getRuntime().availableProcessors(), 3L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            // 线程池的使用方式！
            for (int i = 0; i < 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 使用完毕后需要关闭！
            threadPool.shutdown();
        }
    }
}
