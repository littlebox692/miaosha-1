package com.yan.hello.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Yan on 2016/8/4.
 */
public class MyInvocationHandler implements InvocationHandler {

    private DelegateDao delegateDao;

    public MyInvocationHandler(DelegateDao delegateDao) {
        this.delegateDao = delegateDao;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method ...");
        Object invoke = method.invoke(delegateDao, args);
        System.out.println("after method ...");
        return invoke;
    }
}
