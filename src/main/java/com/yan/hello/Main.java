package com.yan.hello;

import com.sun.javafx.scene.control.skin.VirtualFlow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.*;

/**
 * Created by Yan on 2016/7/9.
 */

class Y {

}

class Fu extends Y {
    String name = "fu";
    int age = 123;

    public Fu() {
    }

    public Fu(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void show() {
        System.out.println("Fu show()...");
    }
}

class Zi extends Fu {

    public Zi() {
    }

    public Zi(String name, int age) {
        super();
    }

    public static void show() {
        System.out.println("Zi show()...");
    }
}

public class Main {
    String name = null;

    public Main() {
    }

    public static void main(String[] args) {
        Zi z = new Zi("A", 12);
        System.out.println(z.name);
    }
}


