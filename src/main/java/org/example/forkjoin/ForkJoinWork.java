package org.example.forkjoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinWork extends RecursiveTask<Long> {
    private Long start;
    private Long end;
    private static final Long tempLong = 10000L; // 临界值：只要超过了这个值 ForkJoin效率就会更好！

    public ForkJoinWork(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    // 计算方法
    @Override
    protected Long compute() {
        // 临界值判断
        if ((end - start) <= tempLong) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else { // 第二种方式
            long middle = (end + start) / 2;
            ForkJoinWork right = new ForkJoinWork(start, middle);
            right.fork(); // 压入线程队列
            ForkJoinWork left = new ForkJoinWork(middle + 1, end);
            left.fork(); // 压入线程队列

            // 获得结果 join 会阻塞等待结果
            return right.join() + left.join();
        }
    }
}
