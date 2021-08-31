package com.example.demo.week4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @auther lxy
 * @Date 2021/08/26 17:13
 */
public class TestCountDownLatchTask {
    public static void main(String[] args) throws Exception {
        int num = 100;
        CountDownLatch latch = new
                CountDownLatch(num);
        List<CompletableFuture> list = new
                ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            CompletableFuture<Void> future =
                    CompletableFuture.runAsync(
                            new CountDownLatchTask(latch));
            list.add(future);
        }
        latch.await();
        for (CompletableFuture future : list) {
            future.get();
        }
    }
}
