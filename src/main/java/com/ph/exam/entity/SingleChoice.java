package com.ph.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 单选题
 *
 * @author : sunmingyao
 * @since : 2022/10/25 09:31
 */

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "t_single_choice", autoResultMap = true)
@JsonTypeName("singleChoice")
public class SingleChoice extends BaseQuestion implements Serializable {
}
