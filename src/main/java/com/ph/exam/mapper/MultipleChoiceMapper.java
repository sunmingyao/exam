package com.ph.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ph.exam.entity.MultipleChoice;
import org.apache.ibatis.annotations.Mapper;

/**
 * 多选持久层
 *
 * @author : sunmingyao
 * @since : 2022/10/28 11:28
 */

@Mapper
public interface MultipleChoiceMapper extends BaseMapper<MultipleChoice> {
}
