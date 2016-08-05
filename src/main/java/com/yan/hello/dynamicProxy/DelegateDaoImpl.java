package com.yan.hello.dynamicProxy;

/**
 * Created by Yan on 2016/8/4.
 */
public class DelegateDaoImpl implements DelegateDao {
    @Override
    public void method() {
        System.out.println("delegateDaoImpl's method ...");
    }
}
