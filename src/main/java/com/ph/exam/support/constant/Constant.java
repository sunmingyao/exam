package com.ph.exam.support.constant;

/**
 * @author : sunmingyao
 */
public class Constant {

    /**
     * 验证码过期时间 此处为五分钟
     */
    public static Integer CODE_EXPIRE_TIME = 5;

    /**
     * jwtToken过期时间
     */
    public static Long TOKEN_EXPIRE_TIME = 365 * 24 * 60 * 60 * 1000L;

    /**
     * 不操作超期
     */
    public static Integer LOGIN_EXPIRE_TIME = 30;

    /**
     * token请求头名称
     */
    public static String TOKEN_HEADER_NAME = "examtoken";


}
