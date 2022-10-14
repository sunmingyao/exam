package com.ph.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 角色
 * 暂时不做业务开发
 *
 * @author : sunmingyao
 * @since : 2022/10/14 09:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_role")
public class Role extends BaseEntity  implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String roleCode;
    private String roleName;
}
