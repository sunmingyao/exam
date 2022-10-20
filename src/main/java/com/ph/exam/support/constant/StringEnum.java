package com.ph.exam.support.constant;

/**
 * @author : sunmingyao
 * 字符串枚举接口
 */
public interface StringEnum<E extends Enum<E>> {
    String getStringValue();
}