package com.ph.exam.support.constant;

/**
 * @author : sunmingyao
 */
public class RedisKey {

    //登录用户
    public static String getLoginUserKey(String account) {
        return "LOGIN:USER:" + account;
    }

    //验证码
    public static String getVerifyImageKey(String verifyCode) {
        return "VERIFY:CODE:" + verifyCode;
    }

}
