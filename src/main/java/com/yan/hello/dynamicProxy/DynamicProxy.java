package com.yan.hello.dynamicProxy;

import java.lang.reflect.Proxy;

/**
 * Created by Yan on 2016/8/4.
 */
public class DynamicProxy {

    public static void main(String[] args) {
        DelegateDao delegateDao = new DelegateDaoImpl();
        MyInvocationHandler handler = new MyInvocationHandler(delegateDao);
        DelegateDao o = (DelegateDao) Proxy.newProxyInstance(delegateDao.getClass().getClassLoader(), delegateDao.getClass().getInterfaces(), handler);
        o.method();
    }
}
