package com.example.demo.week4;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @auther lxy
 * @Date 2021/08/26 17:12
 */
public class CountDownLatchTask implements Runnable{
    private CountDownLatch latch;
    public CountDownLatchTask(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        Integer millis = new Random().nextInt(10000);
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
            this.latch.countDown();
            System.out.println("我的任务OK了:"+Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } }
}
