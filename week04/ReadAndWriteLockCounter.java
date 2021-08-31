package com.example.demo.week4;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @auther lxy
 * @Date 2021/08/26 16:30
 */
public class ReadAndWriteLockCounter {
    private int sum = 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock(true);

    public int incrAndGet() {
        try {
            lock.writeLock().lock();
            return ++sum;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getSum() {
        try {
            lock.readLock().lock();
            return ++sum;
        } finally {
            lock.readLock().unlock();
        }
    }
}
