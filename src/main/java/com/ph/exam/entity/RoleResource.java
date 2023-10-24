package com.ph.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author sunmingyao
 * @since 2023/10/24 16:59
 */

@Data
@TableName("t_role_resource")
public class RoleResource {


    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String resourceId;
    private String roleId;
}

