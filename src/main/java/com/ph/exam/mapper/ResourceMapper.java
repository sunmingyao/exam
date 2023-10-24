package com.ph.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ph.exam.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author sunmingyao
 * @since 2023/10/24 16:53
 */

@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {


}
