package com.minghui.test;

import com.minghui.mapper.AccountMapper;
import com.minghui.session.SelfSqlSession;

/**
 * 测试代理对象
 *
 * @author minghui.y BG358486
 * @create 2019-05-06 18:06
 **/
public class ProxyDemo {

    public static void main(String[] args) {
        AccountMapper mapper = SelfSqlSession.getMapper(AccountMapper.class);
        int num = mapper.insert(4, "灰灰", 500);
        System.out.println("影响行数：" + num);
    }
}
