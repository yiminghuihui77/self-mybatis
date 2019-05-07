package com.minghui.mapper;

import com.minghui.annotation.SelfInsert;
import com.minghui.annotation.SelfParam;

/**
 * @author minghui.y BG358486
 * @create 2019-05-06 17:41
 **/
public interface AccountMapper {

    @SelfInsert("insert into account(id, name, account) values(#{id}, #{name}, #{account})")
    int insert(@SelfParam("id")Integer id, @SelfParam("name")String name, @SelfParam("account")Integer account);
}
