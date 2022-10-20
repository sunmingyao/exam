package com.ph.exam.support.annotation;


import java.lang.annotation.*;

/**
 * 树状处理器类型
 * @author : sunmingyao
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TreeHandlerType {
    String type();
}
