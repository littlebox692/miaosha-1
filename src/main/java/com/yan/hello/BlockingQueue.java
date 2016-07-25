package com.yan.hello;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yan on 2016/7/16.
 */
public class BlockingQueue {
    private List list = new LinkedList<Object>();
    private int limit = 10;

    public synchronized boolean enqueue() {
        while (list.size() == limit) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (list.size() == 0) {
            notifyAll();
        }
        return list.add(new Object());
    }

    public synchronized Object dequeue() {
        while (list.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (list.size() == this.limit) {
            notifyAll();
        }
        return list.remove(0);
    }
}

