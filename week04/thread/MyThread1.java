package com.example.demo.week4.thread;

/**
 * @auther lxy
 * @Date 2021/08/30 14:03
 */
public class MyThread1 implements Runnable {
    @Override
    public void run() {
        System.out.println("这是我的线程2:" + Thread.currentThread().getName());
    }
}
