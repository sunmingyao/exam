package com.ph.exam.support.annotation;


import java.lang.annotation.*;

/**
 * 问题类型
 *
 * @author : sunmingyao
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QuestionType {
    String type();
}
