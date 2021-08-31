package com.example.demo.week4.thread;

/**
 * @auther lxy
 * @Date 2021/08/30 14:00
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("这是我的线程1：" + Thread.currentThread().getName());
    }
}
