package com.ph.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ph.exam.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色持久层
 *
 * @author : sunmingyao
 * @since : 2022/10/14 09:18
 */

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
