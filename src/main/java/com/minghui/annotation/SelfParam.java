package com.minghui.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解：参数
 *
 * @author minghui.y BG358486
 * @create 2019-05-06 17:39
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface SelfParam {
    String value();
}
