package com.minghui.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解：插入
 *
 * @author minghui.y BG358486
 * @create 2019-05-06 17:25
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SelfInsert {
    String value();
}
