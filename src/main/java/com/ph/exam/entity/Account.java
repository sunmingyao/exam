package com.ph.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 账户
 *
 * @author : sunmingyao
 * @since : 2022/10/9 13:49
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_account")
public class Account extends BaseEntity implements Serializable {


    @TableId(type = IdType.AUTO)
    private Long id;

    //用户账号
    private String userName;

    //密码
    private String passWord;

    //用户姓名
    private String nikeName;

    //电话
    private String phoneNumber;

    //盐-用户token加密
    private String salt;

    //是否启用
    private Integer activeFlag;

    //邮箱
    private String email;

}
