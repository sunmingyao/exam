package com.ph.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 基础问题
 *
 * @author : sunmingyao
 * @since : 2022/10/24 09:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "questionType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SingleChoice.class, name = "singleChoice"),
        @JsonSubTypes.Type(value = MultipleChoice.class, name = "multipleChoice"),
        @JsonSubTypes.Type(value = TrueOrFalse.class, name = "trueOrFalse")

})
public class BaseQuestion extends BaseEntity implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String questionType;

    private String questionName;

    private BigDecimal score;

    private List<QuestionOption> optionList; //选项

}
