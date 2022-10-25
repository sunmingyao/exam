package com.ph.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : sunmingyao
 * @since : 2022/10/25 10:40
 */

@Data
@TableName("t_question_option")
public class QuestionOption implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String questionId;  //问题id

    private Integer isAnswer;   //是否是问题答案
}
