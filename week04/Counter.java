package com.example.demo.week4;

/**
 * @auther lxy
 * @Date 2021/08/26 17:00
 */
public class Counter {
    private int num = 0;

    public int addAndGet() {
        return num++;
    }

    public int getNum() {
        return num;
    }
}
