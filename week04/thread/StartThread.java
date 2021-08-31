package com.example.demo.week4.thread;

import java.util.concurrent.*;

/**
 * @auther lxy
 * @Date 2021/08/30 14:00
 */
public class StartThread {
    public static void main(String[] args) {
        // 1
        MyThread myThread1 = new MyThread();
        myThread1.start();

        // 2
        MyThread1 myThread = new MyThread1();
        new Thread(myThread).start();

        // 3
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("这是我的线程3:" + Thread.currentThread().getName());
            }
        });
        thread.start();

        // 4
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("这是我的线程4:" + Thread.currentThread().getName());
                return "1";
            }
        });
        Thread thread1 = new Thread(futureTask);
        thread1.start();

        // 5
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            System.out.println("这是我的线程5:" + Thread.currentThread().getName());
        });
        service.shutdown();

        // 6
        ExecutorService service1 = Executors.newFixedThreadPool(1);
        service1.execute(()->{
            System.out.println("这是我的线程6:"+Thread.currentThread().getName());
        });
        service1.shutdown();

        // 7
        ExecutorService service2 = Executors.newScheduledThreadPool(1);
        service2.execute(()-> System.out.println("这是我的线程7:"+Thread.currentThread().getName()));
        service1.shutdown();

        // 8
        ExecutorService service3 = Executors.newSingleThreadExecutor();
        service2.execute(()-> System.out.println("这是我的线程8:"+Thread.currentThread().getName()));
        service1.shutdown();
    }

}
