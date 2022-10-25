package com.ph.exam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 多选题
 *
 * @author : sunmingyao
 * @since : 2022/10/25 09:50
 */

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "t_multiple_choice", autoResultMap = true)
@JsonTypeName("multipleChoice")
public class MultipleChoice extends BaseQuestion implements Serializable {
}
