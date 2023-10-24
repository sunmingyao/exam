package com.ph.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author sunmingyao
 * @since 2023/10/24 16:49
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_resource")
public class Resource extends BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String resource_name;
    private Long parentId;
    private Integer orderNum;
    private String path;
    private String component;
    private Integer isFrame;
    private String resourceType;
    private Integer status;
    private String perms;
    private String icon;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
    private String remark;
}
