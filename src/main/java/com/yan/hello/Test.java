package com.yan.hello;

import sun.reflect.generics.tree.ReturnType;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by Yan on 2016/8/2.
 */
public class Test {
    public static void main(String[] args) {
        BufferedInputStream bin = new BufferedInputStream(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s1 = "abc";
        String s2 = "a" + "bc";
        String s3 = "a";
        String s4 = "bc";
        String s5 = s3 + s4;

        System.out.println(s1 == s2);
        System.out.println(s5.intern());
        System.out.println(s1 == s5);
        //------------------------------------------------
        //三种方式获得类文件对象
        /**
         * 1.类名.Class
         * 2.对象.getClass()
         * 3.Class.forName()
         */
        Class clazz = Foo.class;
        Foo foo = new Foo();
        Class clazz1 = foo.getClass();
        try {
            Class clazz2 = Class.forName("com.yan.hello.Foo");
            try {
                Object o = clazz2.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            Class re = m.getReturnType();
            re.getSimpleName();
            Parameter[] parameters = m.getParameters();
            parameters.toString();
        }
    }
}

class Foo {
    public Foo() {
    }
}



