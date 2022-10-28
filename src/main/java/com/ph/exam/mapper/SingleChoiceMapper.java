package com.ph.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ph.exam.entity.SingleChoice;
import org.apache.ibatis.annotations.Mapper;

/**
 * 单选持久层
 *
 * @author : sunmingyao
 * @since : 2022/10/28 11:25
 */

@Mapper
public interface SingleChoiceMapper extends BaseMapper<SingleChoice> {
}
