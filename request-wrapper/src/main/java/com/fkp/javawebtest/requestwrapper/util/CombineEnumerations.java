package com.fkp.javawebtest.requestwrapper.util;

import java.util.Enumeration;

/**
 * @author fengkunpeng
 * @version 1.0
 * @description 组合两个Enumeration
 * @date 2024/4/29 11:44
 */
public class CombineEnumerations<T> implements Enumeration<T> {
    private final Enumeration<T> e1;
    private final Enumeration<T> e2;

    public CombineEnumerations(Enumeration<T> e1, Enumeration<T> e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public boolean hasMoreElements() {
        return e1.hasMoreElements() || e2.hasMoreElements();
    }

    @Override
    public T nextElement() {
        if (e1.hasMoreElements()) {
            return e1.nextElement();
        } else if (e2.hasMoreElements()) {
            return e2.nextElement();
        }
        throw new IllegalStateException("No more elements");
    }
}
