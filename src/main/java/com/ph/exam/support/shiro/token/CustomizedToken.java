package com.ph.exam.support.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author : sunmingyao
 * @since : 2022/10/9 14:40
 */
public class CustomizedToken extends UsernamePasswordToken {

    /**
     * 登录类型
     */
    private String loginType;

    public CustomizedToken(final String username, final String password, String loginType) {
        super(username, password);
        this.loginType = loginType;
    }


    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }


}
