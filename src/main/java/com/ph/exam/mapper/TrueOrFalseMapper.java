package com.ph.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ph.exam.entity.TrueOrFalse;
import org.apache.ibatis.annotations.Mapper;

/**
 * 判断题持久层
 *
 * @author : sunmingyao
 * @since : 2022/10/28 11:29
 */

@Mapper
public interface TrueOrFalseMapper extends BaseMapper<TrueOrFalse> {
}
