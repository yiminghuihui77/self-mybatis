package com.minghui.session;

import com.minghui.proxy.MybatisHandler;

import java.lang.reflect.Proxy;

/**
 * @author minghui.y BG358486
 * @create 2019-05-06 17:56
 **/
public class SelfSqlSession {

    public static <T> T getMapper(Class clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] {clazz}, new MybatisHandler(clazz));
    }
}
