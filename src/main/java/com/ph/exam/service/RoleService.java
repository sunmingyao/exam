package com.ph.exam.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ph.exam.entity.Role;
import com.ph.exam.mapper.RoleMapper;
import com.ph.exam.support.exception.ExamException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 角色服务层
 *
 * @author : sunmingyao
 * @since : 2022/10/14 09:21
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> implements IService<Role> {

    @Resource
    private UserRoleService userRoleService;

    /**
     * 创建角色
     *
     * @param role 角色
     * @author SunMingyao
     * @since 2022/10/14 09:30
     */
    public void createRole(Role role) {

        Optional.ofNullable(this.getOne(Wrappers.lambdaQuery(Role.class).eq(Role::getRoleCode, role.getRoleCode()).or().eq(Role::getRoleName, role.getRoleName()))).ifPresent(t -> {
            throw new ExamException(String.format("角色已存在，请核对角色名称[%s]和编码[%s]", t.getRoleName(), t.getRoleCode()));
        });

        this.save(role);
    }
}
