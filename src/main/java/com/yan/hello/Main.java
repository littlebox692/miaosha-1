package com.yan.hello;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Yan on 2016/7/9.
 */

public class Main {
    String name = null;

    public Main() {
    }

    public static void main(String[] args) {
        //-------------------------------------------
        MyThreadFactory myFactory = new MyThreadFactory();
        Thread t = myFactory.newThread(new MyRunnable());
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(20);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, queue, Executors.defaultThreadFactory());
        ExecutorService executorService = Executors.newFixedThreadPool(5, Executors.defaultThreadFactory());
        executorService.execute(new MyRunnable());
        Thread t1 = new Thread(new MyRun(true));
        Thread t2 = new Thread(new MyRun(false));
        t1.start();
        t2.start();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ReentrantReadWriteLock re = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = re.readLock();
        try {
            lock.lock();
            System.out.println();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            condition.signal();
        }

    }

}

class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}

class MyRunnable implements Runnable {

    Object oj = new Object();

    static {
        System.out.println();
    }

    public synchronized void run() {
        synchronized (MyRunnable.class) {
            System.out.println(" this is the synchronized block ...");
        }
        System.out.println("override method run ...");
    }
}


class Mylock {
    static final Object obj1 = new Object();
    static final Object obj2 = new Object();
}

class MyRun implements Runnable {

    private boolean flag;

    public MyRun(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (Mylock.obj1) {
                System.out.println("if obj1 lock ...");
                synchronized (Mylock.obj2) {
                    System.out.println("if obj2 lock");
                }
            }
        } else {
            synchronized (Mylock.obj2) {
                System.out.println("else obj2 lock ...");
                synchronized (Mylock.obj1) {
                    System.out.println("else obj1 lock ...");
                }
            }
        }
    }
}


