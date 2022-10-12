package com.ph.exam.support.annotation;

import java.lang.annotation.*;

/**
 * 当前登录用户
 *
 * @author : sunmingyao
 * @since : 2022/10/12 13:55
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {
}
