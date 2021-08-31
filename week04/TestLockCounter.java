package com.example.demo.week4;

import java.util.stream.IntStream;

/**
 * @auther lxy
 * @Date 2021/08/24 14:39
 */
public class TestLockCounter {
    public static void main(String[] args) {
        int loopNum = 100_0000;
        LockCounter counter = new LockCounter();
        long startTime = System.currentTimeMillis();
        IntStream.range(0, loopNum).parallel().forEach(p -> counter.addAndGet());
        System.out.println("lock--耗时：" + (System.currentTimeMillis() - startTime));

        ReadAndWriteLockCounter lockCounter = new ReadAndWriteLockCounter();
        long startTime1 = System.currentTimeMillis();
        IntStream.range(0, loopNum).parallel().forEach(p -> lockCounter.incrAndGet());
        System.out.println("lockWrite--耗时：" + (System.currentTimeMillis() - startTime1));

        long startTime2 = System.currentTimeMillis();
        IntStream.range(0, loopNum).parallel().forEach(p -> lockCounter.getSum());
        System.out.println("lockRead--耗时：" + (System.currentTimeMillis() - startTime2));

        Counter c = new Counter();
        long startTime3 = System.currentTimeMillis();
        IntStream.range(0, loopNum).parallel().forEach(p -> System.out.println(c.addAndGet()));
        System.out.println("Counter--耗时：" + (System.currentTimeMillis() - startTime3));

    }
}
