package com.example.demo.week4;

import java.util.stream.IntStream;

/**
 * @auther lxy
 * @Date 2021/08/26 17:07
 */
public class TestSemaphoreCounter {
    public static void main(String[] args) {
        int loopNum = 100_0000;
        SemaphoreCounter counter = new SemaphoreCounter();

        long startTime1 = System.currentTimeMillis();
        IntStream.range(0, loopNum).parallel().forEach(p -> counter.incrAndGet());
        System.out.println("lockWrite--耗时：" + (System.currentTimeMillis() - startTime1));

        long startTime2 = System.currentTimeMillis();
        IntStream.range(0, loopNum).parallel().forEach(p -> counter.getSum());
        System.out.println("lockRead--耗时：" + (System.currentTimeMillis() - startTime2));
    }
}
