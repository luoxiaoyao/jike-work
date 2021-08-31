package com.example.demo.week4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther lxy
 * @Date 2021/08/24 14:35
 */
public class LockCounter {
    private int num = 0;
    private Lock lock = new ReentrantLock(true);

    public int addAndGet() {
        lock.lock();
        try {
            return num++;
        } finally {
            lock.unlock();
        }
    }

    public int getNum() {
        return num;
    }
}
