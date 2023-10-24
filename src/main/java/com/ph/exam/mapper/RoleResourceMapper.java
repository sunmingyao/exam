package com.ph.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ph.exam.entity.RoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author sunmingyao
 * @since 2023/10/24 17:03
 */

@Mapper
public interface RoleResourceMapper  extends BaseMapper<RoleResource> {

    /**
     * 获取用户资源列表
     *
     * @param userId 用户id
     * @return java.util.Set<java.lang.String>
     * @author SunMingyao
     * @since 2023/10/24 17:02
     */
    Set<String> getUserPermission(@Param("userId") Long userId);

    Set<String> getUserPermissionByAccount(@Param("userName") String userName);
}
