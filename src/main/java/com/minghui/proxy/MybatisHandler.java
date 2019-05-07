package com.minghui.proxy;

import com.minghui.annotation.SelfInsert;
import com.minghui.annotation.SelfParam;
import com.minghui.tools.JdbcUtils;
import com.minghui.tools.SqlUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 代理handler
 *
 * @author minghui.y BG358486
 * @create 2019-05-06 17:53
 **/
public class MybatisHandler implements InvocationHandler {

    private Object object;

    public MybatisHandler(Object object) {
        this.object = object;
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 被调用的方法
     * @param args 方法的参数值
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用代理对象方法...");
        //获取方法上的注解，提取SQL
        SelfInsert selfInsert = method.getAnnotation(SelfInsert.class);
        if (selfInsert != null) {
            return insertProcessor(method, args, selfInsert);
        }

        return null;
    }

    /**
     * 处理insert注解
     * @param method
     * @param args
     * @param selfInsert
     * @return
     */
    private Object insertProcessor(Method method, Object[] args, SelfInsert selfInsert) {
        String sql = selfInsert.value();

        //获取方法的参数，匹配参数值
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        Parameter[] parameters = method.getParameters();
        String[] paramNames = new String[parameters.length];
        for (int i = 0;i < parameters.length;i++) {
            SelfParam selfParam = parameters[i].getDeclaredAnnotation(SelfParam.class);
            if (selfParam != null) {
                String name = selfParam.value();
                paramNames[i] = name;
                Object value = args[i];
                map.put(name, value);
            }
        }

        //根据SQL中的#{xxx}，按照顺序生成参数数组
        Object[] paramVales = SqlUtils.getParamFormInsertSql(sql, map);
        //将SQL中的#{}替换成?
        String newSql = SqlUtils.getNewSql(sql, paramNames);

        //执行SQL
        return JdbcUtils.insert(newSql, paramVales);
    }
}
