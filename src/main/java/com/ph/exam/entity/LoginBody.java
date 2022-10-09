package com.ph.exam.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 登录实体
 *
 * @author : sunmingyao
 * @since : 2022/10/09 14:13
 */
@Data
public class LoginBody implements Serializable {

    //用户
    private String userName;

    //姓名
    private String nikeName;

    //角色信息
    private Set<String> roleSet;

    private String roleName;

    //权限信息
    private Set<String> permissionSet;

    //token信息
    private String token;

    //签发机构
    private String issuer;

}
