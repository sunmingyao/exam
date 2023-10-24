package com.ph.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ph.exam.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * 用户角色持久层
 *
 * @author : sunmingyao
 * @since : 2022/10/14 09:20
 */

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    Set<String> getUserRoleSet(@Param("userId") Long userId);

    Set<String> getUserRoleSetByAccount(@Param("userName") String userName);
}
