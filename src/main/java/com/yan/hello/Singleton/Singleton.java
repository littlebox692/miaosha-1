package com.yan.hello.Singleton;

/**
 * Created by Yan on 2016/7/21.
 */
//饿汉式单例，但是每次类加载时候就会初始化一个对象，比较浪费资源
class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}

/**
 * 饿汉式的改进，解决类加载时资源浪费问题，使用静态内部类初始化，
 * 类加载时候并不会初始化对象，只在第一次调用方法时初始化
 */
class Singleton2 {
    private Singleton2() {
    }

    private static class InnerSingleton {
        private static final Singleton2 instance = new Singleton2();
    }

    public static Singleton2 getInstance() {
        return InnerSingleton.instance;
    }
}

// 懒汉式单例，使用双重检查减少同步代码行数，提高多线程时的性能
class Singleton3 {
    private static Singleton3 instance = null;

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        if (instance == null) {//双重检查，如果为空进入同步块，否则直接返回实例，提高了多线程的性能
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
