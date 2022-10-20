package com.ph.exam.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * 树状结构
 *
 * @author : sunmingyao
 * @since : 2020/10/10 18:02
 *
 */
@Data
public class BaseTree {

    @TableField(exist = false)
    private String code;

    @TableField(exist = false)
    private String name;

    @TableField(exist = false)
    private String parentCode;

    @TableField(exist = false)
    private Integer level;

    @TableField(exist = false)
    private List<? extends BaseTree> childList;
}
