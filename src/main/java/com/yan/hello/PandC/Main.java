package com.yan.hello.PandC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yan on 2016/7/30.
 */
class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[10];
    int putptr, takeptr, count;

    public void put(Object x) {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[putptr] = x;
            System.out.println("put " + putptr);
            if (++putptr == items.length) {
                putptr = 0;
            }
            notEmpty.signal();
            count++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Object take() {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object x = items[takeptr];
            System.out.println("take " + takeptr);
            if (++takeptr == items.length) {
                takeptr = 0;
            }
            count--;
            notFull.signal();
            return x;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }
}

class BoundedFactory {
    private BoundedFactory() {
    }

    private static BoundedBuffer b = null;

    public static BoundedBuffer getBounde() {
        if (b == null) {
            synchronized (BoundedFactory.class) {
                if (b == null) {
                    b = new BoundedBuffer();
                }
            }
        }
        return b;
    }
}

public class Main {
    public static void main(String[] args) {
        Thing t = new Thing();
        P p = new P(t);
        C c = new C(t);
        for (int i = 0; i < 10; i++) {
            Thread pThread = new Thread(p, "pThread:" + i);
            Thread cThread = new Thread(c, "cThread:" + i);
            pThread.start();
            cThread.start();
        }
    }
}

class Thing {
    int num = 0;
    int max = 10;

    public void add() {
        synchronized (this) {
            while (max == num) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num++;
            System.out.println(Thread.currentThread().getName() + " num=" + num);
            notifyAll();
        }
    }

    public void sub() {
        synchronized (this) {
            while (num == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num--;
            System.out.println(Thread.currentThread().getName() + " num=" + num);
            notifyAll();
        }
    }
}

class P implements Runnable {
    private Thing t;

    public P(Thing t) {
        this.t = t;
    }

    public void run() {
        t.add();
    }
}

class C implements Runnable {
    private Thing t;

    public C(Thing t) {
        this.t = t;
    }

    public void run() {
        t.sub();
    }
}


