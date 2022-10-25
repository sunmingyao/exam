package com.ph.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 判断题
 *
 * @author : sunmingyao
 * @since : 2022/10/25 10:01
 */

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "t_true_false", autoResultMap = true)
@JsonTypeName("trueOrFalse")
public class TrueOrFalse extends BaseQuestion implements Serializable {
}
