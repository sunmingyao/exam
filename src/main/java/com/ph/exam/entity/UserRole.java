package com.ph.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色表
 * 暂时不做业务开发
 *
 * @author : sunmingyao
 * @since : 2022/10/14 09:08
 */

@Data
@TableName("t_user_role")
public class UserRole implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String userId;
    private String roleId;
}
