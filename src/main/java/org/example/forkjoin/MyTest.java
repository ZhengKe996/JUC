package org.example.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class MyTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();
        test2();
        test3();
    }

    private static void test1() {
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (Long i = 0L; i <= 20_0000_0000L; i++) sum += i;
        long end = System.currentTimeMillis();
        System.out.println("times:" + (end - start) + " r=>" + sum);
    }

    private static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinWork forkJoinWork = new ForkJoinWork(0L, 20_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinWork);
        Long sum = submit.get();

        long end = System.currentTimeMillis();
        System.out.println("times:" + (end - start) + " r=>" + sum);
    }

    private static void test3() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 20_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("times:" + (end - start) + " r=>" + sum);
    }


}
